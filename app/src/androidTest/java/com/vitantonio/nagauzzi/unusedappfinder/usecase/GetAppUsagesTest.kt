package com.vitantonio.nagauzzi.unusedappfinder.usecase

import com.vitantonio.nagauzzi.unusedappfinder.model.equalsWithoutIcon
import com.vitantonio.nagauzzi.unusedappfinder.repository.ErrorAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.MockAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.result.AppUsageResult
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
        val result = getAppUsages()

        // Check output
        val success = result as AppUsageResult.Success
        assertTrue(success.list.equalsWithoutIcon(MockAppUsageRepository().get()))
    }

    @Test
    fun test_error() = runTest {
        // Initialize
        val getAppUsages = GetAppUsages(ErrorAppUsageRepository())

        // Input
        val result = getAppUsages()

        // Check output
        assertTrue(result is AppUsageResult.Error)
    }
}