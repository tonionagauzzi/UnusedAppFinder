package com.vitantonio.nagauzzi.unusedappfinder.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyAppUsages
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppUsageTest {
    @Test
    fun test_equalsWithoutIcon() {
        // Initialize
        val context: Context = ApplicationProvider.getApplicationContext()

        // Input
        val appUsageList = context.dummyAppUsages(useDummyIcon = false)

        // Check output
        val expectedAppUsageList = context.dummyAppUsages(useDummyIcon = false).map {
            it.copy(icon = mockk())
        }
        appUsageList.equalsWithoutIcon(expectedAppUsageList)
    }
}
