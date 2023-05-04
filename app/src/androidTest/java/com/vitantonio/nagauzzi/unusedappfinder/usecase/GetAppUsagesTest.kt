package com.vitantonio.nagauzzi.unusedappfinder.usecase

import com.vitantonio.nagauzzi.unusedappfinder.model.equalsWithoutIcon
import com.vitantonio.nagauzzi.unusedappfinder.repository.ErrorAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.MockAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Error
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAppUsagesTest {
    @Test
    fun test_success() = runTest {
        // Initialize
        val getAppUsages = GetAppUsages(MockAppUsageRepository())

        // Input
        getAppUsages()

        // Check output
        val result = AppUsageState.now.value as Success
        assertTrue(result.list.equalsWithoutIcon(MockAppUsageRepository().get()))
    }

    @Test
    fun test_error() = runTest {
        // Initialize
        val getAppUsages = GetAppUsages(ErrorAppUsageRepository())

        // Input
        getAppUsages()

        // Check output
        AppUsageState.now.value as Error
    }
}