package com.vitantonio.nagauzzi.unusedappfinder.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.ErrorAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockPackageNameRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetFilteredAndSortedAppUsagesTest {
    @Test
    fun test_success_with_filter_and_sort() = runTest {
        // Initialize
        val getFilteredAndSortedAppUsages = GetFilteredAndSortedAppUsages(
            MockAppUsageRepository(),
            MockPackageNameRepository()
        )

        // Input
        val result = getFilteredAndSortedAppUsages()

        // Check output
        assertTrue(result.isSuccess)
        val appUsageList = result.getOrNull()
        assertNotNull(appUsageList)

        // フィルタリングとソートが適用された期待値を作成
        val expectedAppUsageList = AppUsage.dummyList()
            .sortedByDescending { appUsage ->
                if (appUsage.lastUsedTime > 0) appUsage.lastUsedTime else appUsage.installedTime
            }
        assertEquals(expectedAppUsageList, appUsageList)
    }

    @Test
    fun test_error() = runTest {
        // Initialize
        val getFilteredAndSortedAppUsages = GetFilteredAndSortedAppUsages(
            ErrorAppUsageRepository(),
            MockPackageNameRepository()
        )

        // Input
        val result = getFilteredAndSortedAppUsages()

        // Check output
        assertTrue(result.isFailure)
    }
}
