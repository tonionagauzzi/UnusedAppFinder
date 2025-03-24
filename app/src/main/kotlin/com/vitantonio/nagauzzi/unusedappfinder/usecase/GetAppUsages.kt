package com.vitantonio.nagauzzi.unusedappfinder.usecase

import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.result.AppUsageResult
import javax.inject.Inject

class GetAppUsages @Inject constructor(
    private val repository: AppUsageRepository,
) {
    operator fun invoke(): AppUsageResult {
        return try {
            val appUsageList = repository.get()
            AppUsageResult.Success(appUsageList)
        } catch (e: Exception) {
            AppUsageResult.Error(e)
        }
    }
}
