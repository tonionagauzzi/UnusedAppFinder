package com.vitantonio.nagauzzi.unusedappfinder.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyIcon2
import org.junit.Test

class AppUsageTest {
    @Test
    fun test_equalsWithoutIcon() {
        // Initialize
        val context: Context = ApplicationProvider.getApplicationContext()

        // Input
        val appUsageList = context.dummyAppUsages()

        // Check output
        val expectedAppUsageList = context.dummyAppUsages().map {
            it.copy(icon = context.dummyIcon2())
        }
        appUsageList.equalsWithoutIcon(expectedAppUsageList)
    }
}
