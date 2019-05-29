package com.vitantonio.nagauzzi.unusedappfinder.repository

import android.content.Context
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import android.app.usage.UsageStatsManager
import java.util.*
import android.content.pm.PackageManager
import com.vitantonio.nagauzzi.unusedappfinder.extension.changeYear
import android.content.pm.ApplicationInfo

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
        val to = Date()
        val from = to.changeYear(-1)
        val queryUsageStats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_MONTHLY,
            from.time,
            to.time
        )
        if (queryUsageStats.size == 0) {
            // Something is wrong
            throw SecurityException("UsageStatsManager.queryUsageStats() returned empty list.")
        }

        // Get installed app list
        val packageManager = context.packageManager
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA).map { appInfo ->
            // Generate AppUsage list (by usage stats list and installed app list)
            AppUsage(
                name = packageManager.getApplicationLabel(appInfo).toString(),
                lastUsedTime = queryUsageStats.filter {
                    it.packageName == appInfo.packageName
                }.maxBy {
                    it.lastTimeUsed
                }?.lastTimeUsed ?: 0,
                enableUninstall = appInfo.isUserApp()
            )
        }
    }
}

private fun ApplicationInfo.isUserApp(): Boolean {
    val mask = ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
    return flags and mask == 0
}