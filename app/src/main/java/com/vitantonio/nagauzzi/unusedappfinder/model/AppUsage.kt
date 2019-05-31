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
