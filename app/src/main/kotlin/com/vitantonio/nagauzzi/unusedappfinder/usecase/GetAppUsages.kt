package com.vitantonio.nagauzzi.unusedappfinder.usecase

import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository
import javax.inject.Inject

class GetAppUsages @Inject constructor(
    private val repository: AppUsageRepository,
) {
    operator fun invoke(): Result<List<AppUsage>> = runCatching { repository.get() }
}
