package com.vitantonio.nagauzzi.unusedappfinder.model

data class AppUsage(
    val name: String,
    val lastUsedTime: Long,
    val enableUninstall: Boolean
)
