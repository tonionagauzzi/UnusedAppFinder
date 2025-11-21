package com.vitantonio.nagauzzi.unusedappfinder.view.model

import android.graphics.drawable.Drawable

data class AppUsageUiModel(
    val name: String,
    val packageName: String,
    val activityName: String,
    val icon: Drawable?,
    val installedTime: Long,
    val lastUsedTime: Long,
    val enableUninstall: Boolean,
) {
    companion object {
        fun dummyList(icon: Drawable? = null) = listOf(
            AppUsageUiModel(
                name = "name0",
                packageName = "packageName0",
                activityName = "activityName0",
                icon = icon,
                installedTime = 0,
                lastUsedTime = 0,
                enableUninstall = true
            ),
            AppUsageUiModel(
                name = "name1",
                packageName = "packageName1",
                activityName = "activityName1",
                icon = icon,
                installedTime = 86400000,
                lastUsedTime = 86400000,
                enableUninstall = true
            ),
            AppUsageUiModel(
                name = "name2",
                packageName = "packageName2",
                activityName = "activityName2",
                icon = icon,
                installedTime = 172800000,
                lastUsedTime = 172800000,
                enableUninstall = true
            ),
            AppUsageUiModel(
                name = "name3",
                packageName = "packageName3",
                activityName = "activityName3",
                icon = icon,
                installedTime = 259200000,
                lastUsedTime = 259200000,
                enableUninstall = true
            ),
            AppUsageUiModel(
                name = "name4",
                packageName = "packageName4",
                activityName = "activityName4",
                icon = icon,
                installedTime = 345600000,
                lastUsedTime = 345600000,
                enableUninstall = true
            )
        )
    }
}
