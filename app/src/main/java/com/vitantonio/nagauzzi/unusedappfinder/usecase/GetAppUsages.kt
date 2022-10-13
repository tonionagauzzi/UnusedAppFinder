package com.vitantonio.nagauzzi.unusedappfinder.usecase

import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Error
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Success
import javax.inject.Inject

class GetAppUsages @Inject constructor(
    private val repository: AppUsageRepository
) {
    suspend fun execute() {
        try {
            val appUsageList = repository.get()
            AppUsageState.update(Success(appUsageList))
        } catch (e: SecurityException) {
            AppUsageState.update(Error(e))
        }
    }
}
