package com.vitantonio.nagauzzi.unusedappfinder.result

import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage

sealed class AppUsageResult {
    data class Success(val list: List<AppUsage>) : AppUsageResult()
    data class Error(val exception: Exception) : AppUsageResult()
}
