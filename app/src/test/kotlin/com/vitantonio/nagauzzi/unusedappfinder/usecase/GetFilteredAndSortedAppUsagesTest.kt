package com.vitantonio.nagauzzi.unusedappfinder.usecase

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.model.equalsWithoutIcon
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.ErrorAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockPackageNameRepository
import kotlinx.coroutines.test.runTest
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
        val context: Context = ApplicationProvider.getApplicationContext()

        // Input
        val result = getFilteredAndSortedAppUsages()

        // Check output
        assertTrue(result.isSuccess)
        result.getOrNull()?.let { appUsageList ->
            // フィルタリングとソートが適用された期待値を作成
            val expectedAppUsageList = context.dummyAppUsages(useDummyIcon = false)
                .sortedByDescending { appUsage ->
                    if (appUsage.lastUsedTime > 0) appUsage.lastUsedTime else appUsage.installedTime
                }
            assertTrue(appUsageList.equalsWithoutIcon(expectedAppUsageList))
        }
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
