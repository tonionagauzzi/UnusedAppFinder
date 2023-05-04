package com.vitantonio.nagauzzi.unusedappfinder.repository

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.test.core.app.ApplicationProvider
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage

class MockAppUsageRepository: AppUsageRepository {
    override fun get(): List<AppUsage> {
        val context: Context = ApplicationProvider.getApplicationContext()
        return listOf(
            AppUsage(
                name = "name0",
                packageName = "packageName0",
                activityName = "activityName0",
                icon = AppCompatResources.getDrawable(context, R.drawable.ic_launcher_foreground)!!,
                installedTime = 0,
                lastUsedTime = 0,
                enableUninstall = true
            ),
            AppUsage(
                name = "name1",
                packageName = "packageName1",
                activityName = "activityName1",
                icon = AppCompatResources.getDrawable(context, R.drawable.ic_launcher_foreground)!!,
                installedTime = 2,
                lastUsedTime = 2,
                enableUninstall = true
            )
        )
    }
}

class ErrorAppUsageRepository: AppUsageRepository {
    override fun get(): List<AppUsage> {
        throw SecurityException("Dummy security exception")
    }
}
