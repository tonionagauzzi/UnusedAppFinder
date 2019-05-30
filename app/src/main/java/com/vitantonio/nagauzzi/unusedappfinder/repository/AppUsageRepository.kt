package com.vitantonio.nagauzzi.unusedappfinder.repository

import android.content.Context
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import android.app.usage.UsageStatsManager
import java.util.*
import android.content.pm.ApplicationInfo
import android.content.Intent
import com.vitantonio.nagauzzi.unusedappfinder.extension.*

interface AppUsageRepository {
    fun get(): List<AppUsage>
}

class AppUsageRepositoryImpl(
    private val context: Context
) : AppUsageRepository {

    override fun get(): List<AppUsage> {

        // Get usage stats list
        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val calendar = Calendar.getInstance()
        val today = Date()
        val to = today.changeDay(1).resetDateToZeroOClock(calendar).changeMillisecond(-1)
        val from = Date().changeMonth(-5, calendar).resetDateToStartDayOfMonth(calendar).resetDateToZeroOClock(calendar)
        val queryUsageStats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_MONTHLY,
            from.time,
            to.time
        )
        if (queryUsageStats.size == 0) {
            // Something is wrong
            throw SecurityException("UsageStatsManager.queryUsageStats() returned empty list.")
        }
        // TODO It seems not to be able to get usage stats before recent shutdown...
        //val oldestStats = queryUsageStats.filter { it.lastTimeUsed > 1000000000000 }.minBy { it.lastTimeUsed }

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
                lastUsedTime = queryUsageStats.filter {
                    it.packageName == resolveInfo.activityInfo.packageName
                }.maxBy {
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