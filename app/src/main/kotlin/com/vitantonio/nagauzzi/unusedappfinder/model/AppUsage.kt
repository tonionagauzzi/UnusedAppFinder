package com.vitantonio.nagauzzi.unusedappfinder.model

data class AppUsage(
    val name: String,
    val packageName: String,
    val activityName: String,
    val installedTime: Long,
    val lastUsedTime: Long,
    val enableUninstall: Boolean,
) {
    companion object {
        fun dummyList() = listOf(
            AppUsage(
                name = "name0",
                packageName = "packageName0",
                activityName = "activityName0",
                installedTime = 0,
                lastUsedTime = 0,
                enableUninstall = true
            ),
            AppUsage(
                name = "name1",
                packageName = "packageName1",
                activityName = "activityName1",
                installedTime = 86400000,
                lastUsedTime = 86400000,
                enableUninstall = true
            ),
            AppUsage(
                name = "name2",
                packageName = "packageName2",
                activityName = "activityName2",
                installedTime = 172800000,
                lastUsedTime = 172800000,
                enableUninstall = true
            ),
            AppUsage(
                name = "name3",
                packageName = "packageName3",
                activityName = "activityName3",
                installedTime = 259200000,
                lastUsedTime = 259200000,
                enableUninstall = true
            ),
            AppUsage(
                name = "name4",
                packageName = "packageName4",
                activityName = "activityName4",
                installedTime = 345600000,
                lastUsedTime = 345600000,
                enableUninstall = true
            )
        )
    }
}
