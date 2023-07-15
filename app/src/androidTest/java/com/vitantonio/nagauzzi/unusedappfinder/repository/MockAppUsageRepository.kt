package com.vitantonio.nagauzzi.unusedappfinder.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.vitantonio.nagauzzi.unusedappfinder.extension.getFakeIcon
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage

class MockAppUsageRepository : AppUsageRepository {
    override fun get(): List<AppUsage> {
        val context: Context = ApplicationProvider.getApplicationContext()
        return listOf(
            AppUsage(
                name = "name0",
                packageName = "packageName0",
                activityName = "activityName0",
                icon = context.getFakeIcon(),
                installedTime = 0,
                lastUsedTime = 0,
                enableUninstall = true
            ),
            AppUsage(
                name = "name1",
                packageName = "packageName1",
                activityName = "activityName1",
                icon = context.getFakeIcon(),
                installedTime = 2,
                lastUsedTime = 2,
                enableUninstall = true
            ),
            AppUsage(
                name = "name2",
                packageName = "packageName2",
                activityName = "activityName2",
                icon = context.getFakeIcon(),
                installedTime = 1,
                lastUsedTime = 1,
                enableUninstall = true
            )
        )
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
