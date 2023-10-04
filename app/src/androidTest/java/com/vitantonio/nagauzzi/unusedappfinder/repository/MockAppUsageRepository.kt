package com.vitantonio.nagauzzi.unusedappfinder.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.model.mock.mockAppUsages

class MockAppUsageRepository : AppUsageRepository {
    override fun get(): List<AppUsage> {
        val context: Context = ApplicationProvider.getApplicationContext()
        return context.mockAppUsages()
    }
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
