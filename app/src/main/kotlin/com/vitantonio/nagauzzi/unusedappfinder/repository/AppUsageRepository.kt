package com.vitantonio.nagauzzi.unusedappfinder.repository

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.ResolveInfo
import com.vitantonio.nagauzzi.unusedappfinder.extension.minusMonths
import com.vitantonio.nagauzzi.unusedappfinder.extension.resetDateToStartDayOfMonth
import com.vitantonio.nagauzzi.unusedappfinder.extension.resetTimeToEndOfDay
import com.vitantonio.nagauzzi.unusedappfinder.extension.resetTimeToStartOfDay
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Instant
import javax.inject.Inject

private const val VALID_TIMESTAMP_THRESHOLD = 946652400000L // 2000/01/01 00:00:00

interface AppUsageRepository {
    fun get(): List<AppUsage>
}

class AppUsageRepositoryImpl
    @Inject
    constructor(
        @param:ApplicationContext private val context: Context,
    ) : AppUsageRepository {
        override fun get(): List<AppUsage> {
            val usageStats = getUsageStats()
            val installedApps = getInstalledApps()
            return createAppUsageList(installedApps, usageStats)
        }

        private data class PackageUsageStats(
            val packageName: String,
            val lastUsedTime: Long,
        )

        private fun getUsageStats(): List<PackageUsageStats> {
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
            if (queryUsageStats.isEmpty()) {
                throw SecurityException("UsageStatsManager.queryUsageStats() returned empty list.")
            }

            return queryUsageStats
                .filter { it.lastTimeUsed >= VALID_TIMESTAMP_THRESHOLD }
                .groupBy { it.packageName }
                .map { (packageName, stats) ->
                    PackageUsageStats(
                        packageName = packageName,
                        lastUsedTime = stats.maxOf { it.lastTimeUsed }
                    )
                }
        }

        private fun getInstalledApps(): List<ResolveInfo> {
            val packageManager = context.packageManager
            val mainIntent = Intent(Intent.ACTION_MAIN, null)
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            return packageManager.queryIntentActivities(mainIntent, 0)
        }

        private fun createAppUsageList(
            installedApps: List<ResolveInfo>,
            usageStats: List<PackageUsageStats>,
        ): List<AppUsage> {
            val packageManager = context.packageManager
            return installedApps.map { resolveInfo ->
                AppUsage(
                    name = resolveInfo.loadLabel(packageManager).toString(),
                    packageName = resolveInfo.activityInfo.packageName,
                    activityName = resolveInfo.activityInfo.name,
                    icon = resolveInfo.loadIcon(packageManager),
                    installedTime = packageManager.getPackageInfo(
                        resolveInfo.activityInfo.packageName,
                        0
                    ).firstInstallTime,
                    lastUsedTime = usageStats.find { it.packageName == resolveInfo.activityInfo.packageName }?.lastUsedTime ?: 0,
                    enableUninstall = resolveInfo.activityInfo.applicationInfo.isUserApp()
                )
            }
        }
    }

private fun ApplicationInfo.isUserApp(): Boolean {
    val mask = ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
    return flags and mask == 0
}
