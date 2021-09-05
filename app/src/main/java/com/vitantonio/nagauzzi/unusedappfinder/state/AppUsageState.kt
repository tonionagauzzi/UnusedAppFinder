package com.vitantonio.nagauzzi.unusedappfinder.state

import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class AppUsageState {
    companion object {
        private val mutableNow = MutableStateFlow(Init() as AppUsageState)
        val now: StateFlow<AppUsageState> = mutableNow
        suspend fun update(to: AppUsageState) {
            mutableNow.emit(to)
        }
    }

    data class Init(val list: List<AppUsage> = emptyList()) : AppUsageState()
    data class Success(val list: List<AppUsage>) : AppUsageState()
    data class Error(val exception: Exception) : AppUsageState()
}
