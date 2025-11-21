package com.vitantonio.nagauzzi.unusedappfinder.usecase

import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.PackageNameRepository
import javax.inject.Inject

class GetFilteredAndSortedAppUsages
    @Inject
    constructor(
        private val appUsageRepository: AppUsageRepository,
        private val packageNameRepository: PackageNameRepository,
    ) {
        operator fun invoke(): Result<List<AppUsage>> =
            runCatching {
                appUsageRepository.get()
                    .filter { appUsage ->
                        appUsage.enableUninstall && appUsage.packageName != packageNameRepository.get()
                    }.sortedByDescending { appUsage ->
                        if (appUsage.lastUsedTime > 0) appUsage.lastUsedTime else appUsage.installedTime
                    }
            }
    }
