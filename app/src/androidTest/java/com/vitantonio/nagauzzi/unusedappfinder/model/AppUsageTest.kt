package com.vitantonio.nagauzzi.unusedappfinder.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.vitantonio.nagauzzi.unusedappfinder.extension.getFakeIcon
import org.junit.Test

class AppUsageTest {
    @Test
    fun test_equalsWithoutIcon() {
        // Initialize
        val context: Context = ApplicationProvider.getApplicationContext()

        // Input
        val appUsageList = listOf(
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

        // Check output
        val expectedAppUsageList = listOf(
            // Only the icon is different.
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
        appUsageList.equalsWithoutIcon(expectedAppUsageList)
    }
}
