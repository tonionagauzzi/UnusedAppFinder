package com.vitantonio.nagauzzi.unusedappfinder.repository.mock

import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository

class MockAppUsageRepository : AppUsageRepository {
    override fun get(): List<AppUsage> = AppUsage.dummyList()
}

class ProhibitedAppUsageRepository : AppUsageRepository {
    override fun get(): List<AppUsage> {
        throw SecurityException("Dummy security exception")
    }
}

class ErrorAppUsageRepository : AppUsageRepository {
    override fun get(): List<AppUsage> {
        throw IllegalStateException("Dummy illegal state exception")
    }
}
