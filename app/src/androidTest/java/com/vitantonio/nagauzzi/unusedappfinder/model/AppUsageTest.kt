package com.vitantonio.nagauzzi.unusedappfinder.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.vitantonio.nagauzzi.unusedappfinder.extension.getFakeIcon2
import com.vitantonio.nagauzzi.unusedappfinder.model.mock.mockAppUsages
import org.junit.Test

class AppUsageTest {
    @Test
    fun test_equalsWithoutIcon() {
        // Initialize
        val context: Context = ApplicationProvider.getApplicationContext()

        // Input
        val appUsageList = context.mockAppUsages()

        // Check output
        val expectedAppUsageList = context.mockAppUsages().map {
            it.copy(icon = context.getFakeIcon2())
        }
        appUsageList.equalsWithoutIcon(expectedAppUsageList)
    }
}
