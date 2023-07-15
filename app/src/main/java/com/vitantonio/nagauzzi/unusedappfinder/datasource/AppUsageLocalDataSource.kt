package com.vitantonio.nagauzzi.unusedappfinder.datasource

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import com.vitantonio.nagauzzi.unusedappfinder.extension.minusMonths
import com.vitantonio.nagauzzi.unusedappfinder.extension.resetDateToStartDayOfMonth
import com.vitantonio.nagauzzi.unusedappfinder.extension.resetTimeToEndOfDay
import com.vitantonio.nagauzzi.unusedappfinder.extension.resetTimeToStartOfDay
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Instant
import javax.inject.Inject

class AppUsageLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun get(): List<AppUsage> {
        // Get usage stats list
        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val today = Instant.now()
        val to = today.resetTimeToEndOfDay()
        val from = today.minusMonths(5)
            .resetDateToStartDayOfMonth()
            .resetTimeToStartOfDay()
        val queryUsageStats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_MONTHLY,
            from.toEpochMilli(),
            to.toEpochMilli()
        )
        if (queryUsageStats.size == 0) {
            // Something is wrong
            throw SecurityException("UsageStatsManager.queryUsageStats() returned empty list.")
        }
        // FIXME It seems not to be able to get usage stats before recent shutdown...
        // val oldestStats = queryUsageStats.filter { it.lastTimeUsed > 1000000000000 }.minBy { it.lastTimeUsed }

        // Get installed app list
        val packageManager = context.packageManager
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val resolveInfoList = packageManager.queryIntentActivities(mainIntent, 0)

        // Generate AppUsage list (by usage stats list and installed app list)
        return resolveInfoList.map { resolveInfo ->
            AppUsage(
                name = resolveInfo.loadLabel(packageManager).toString(),
                packageName = resolveInfo.activityInfo.packageName,
                activityName = resolveInfo.activityInfo.name,
                icon = resolveInfo.loadIcon(packageManager),
                installedTime = packageManager.getPackageInfo(
                    resolveInfo.activityInfo.packageName,
                    0
                ).firstInstallTime,
                lastUsedTime = queryUsageStats.filter {
                    it.packageName == resolveInfo.activityInfo.packageName &&
                        it.lastTimeUsed >= 946652400000 // 2000/01/01 00:00:00以降なら正しいデータとみなす
                }.maxByOrNull {
                    it.lastTimeUsed
                }?.lastTimeUsed ?: 0,
                enableUninstall = resolveInfo.activityInfo.applicationInfo.isUserApp()
            )
        }
    }
}

private fun ApplicationInfo.isUserApp(): Boolean {
    val mask = ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
    return flags and mask == 0
}
