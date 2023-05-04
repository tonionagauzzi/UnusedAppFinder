package com.vitantonio.nagauzzi.unusedappfinder.model

import android.graphics.drawable.Drawable

data class AppUsage(
    val name: String,
    val packageName: String,
    val activityName: String,
    val icon: Drawable,
    val installedTime: Long,
    val lastUsedTime: Long,
    val enableUninstall: Boolean
)

fun AppUsage.equalsWithoutIcon(expected: AppUsage) = name == expected.name &&
        packageName == expected.packageName && activityName == expected.activityName &&
        installedTime == expected.installedTime && lastUsedTime == expected.lastUsedTime &&
        enableUninstall == expected.enableUninstall

fun List<AppUsage>.equalsWithoutIcon(expected: List<AppUsage>) = List(size) { index ->
    this[index].equalsWithoutIcon(expected[index])
}.all { it }
