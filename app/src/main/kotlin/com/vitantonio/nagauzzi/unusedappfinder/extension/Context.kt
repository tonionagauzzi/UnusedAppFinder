package com.vitantonio.nagauzzi.unusedappfinder.extension

import android.content.Context
import android.graphics.drawable.Drawable
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage

private fun Context.dummyIcon(): Drawable = getDrawable(R.drawable.ic_launcher_foreground)!!

// FIXME: Make this a static extension function of AppUsage when Kotlin 2.0 comes out.
fun Context.dummyAppUsages(useDummyIcon: Boolean) = listOf(
    AppUsage(
        name = "name0",
        packageName = "packageName0",
        activityName = "activityName0",
        icon = if (useDummyIcon) dummyIcon() else null,
        installedTime = 0,
        lastUsedTime = 0,
        enableUninstall = true
    ),
    AppUsage(
        name = "name1",
        packageName = "packageName1",
        activityName = "activityName1",
        icon = if (useDummyIcon) dummyIcon() else null,
        installedTime = 86400000,
        lastUsedTime = 86400000,
        enableUninstall = true
    ),
    AppUsage(
        name = "name2",
        packageName = "packageName2",
        activityName = "activityName2",
        icon = if (useDummyIcon) dummyIcon() else null,
        installedTime = 172800000,
        lastUsedTime = 172800000,
        enableUninstall = true
    ),
    AppUsage(
        name = "name3",
        packageName = "packageName3",
        activityName = "activityName3",
        icon = if (useDummyIcon) dummyIcon() else null,
        installedTime = 259200000,
        lastUsedTime = 259200000,
        enableUninstall = true
    ),
    AppUsage(
        name = "name4",
        packageName = "packageName4",
        activityName = "activityName4",
        icon = if (useDummyIcon) dummyIcon() else null,
        installedTime = 345600000,
        lastUsedTime = 345600000,
        enableUninstall = true
    )
)
