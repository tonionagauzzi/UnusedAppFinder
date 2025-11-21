package com.vitantonio.nagauzzi.unusedappfinder.view.model

import android.content.Context
import android.graphics.drawable.Drawable
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage

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
        fun dummyList(icon: Drawable? = null) =
            AppUsage.dummyList().map { appUsage ->
                AppUsageUiModel(
                    name = appUsage.name,
                    packageName = appUsage.packageName,
                    activityName = appUsage.activityName,
                    icon = icon,
                    installedTime = appUsage.installedTime,
                    lastUsedTime = appUsage.lastUsedTime,
                    enableUninstall = appUsage.enableUninstall
                )
            }
    }
}

fun AppUsage.toUiModel(context: Context): AppUsageUiModel {
    val packageManager = context.packageManager
    val icon = runCatching {
        packageManager.getApplicationIcon(packageName)
    }.getOrNull()
    return AppUsageUiModel(
        name = name,
        packageName = packageName,
        activityName = activityName,
        icon = icon,
        installedTime = installedTime,
        lastUsedTime = lastUsedTime,
        enableUninstall = enableUninstall
    )
}
