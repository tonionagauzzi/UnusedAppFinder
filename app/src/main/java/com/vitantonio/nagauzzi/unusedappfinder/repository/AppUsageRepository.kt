package com.vitantonio.nagauzzi.unusedappfinder.repository

import com.vitantonio.nagauzzi.unusedappfinder.datasource.AppUsageLocalDataSource
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import javax.inject.Inject

interface AppUsageRepository {
    fun get(): List<AppUsage>
}

class AppUsageRepositoryImpl @Inject constructor(
    private val dataSource: AppUsageLocalDataSource,
) : AppUsageRepository {
    override fun get() = dataSource.get()
}
