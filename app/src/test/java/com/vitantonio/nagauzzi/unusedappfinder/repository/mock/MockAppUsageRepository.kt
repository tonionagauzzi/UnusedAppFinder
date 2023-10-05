package com.vitantonio.nagauzzi.unusedappfinder.repository.mock

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository
import io.mockk.mockk

class MockAppUsageRepository : AppUsageRepository {
    override fun get(): List<AppUsage> {
        val context: Context = ApplicationProvider.getApplicationContext()
        return context.dummyAppUsages(dummyIcon = mockk())
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
