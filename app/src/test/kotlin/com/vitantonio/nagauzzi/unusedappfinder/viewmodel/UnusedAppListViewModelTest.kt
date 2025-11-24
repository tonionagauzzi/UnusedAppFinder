package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.ErrorAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockPackageNameRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.ProhibitedAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetFilteredAndSortedAppUsages
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UnusedAppListViewModelTest {
    @Test
    fun test_sort_app_usage_list() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(
            GetFilteredAndSortedAppUsages(
                MockAppUsageRepository(),
                MockPackageNameRepository()
            )
        )

        // Input
        viewModel.reload()

        // Check output
        val expectedAppUsageList = AppUsage.dummyList().sortedByDescending {
            it.lastUsedTime
        }
        val appUsageList = viewModel.appUsageList.first()
        assertEquals(expectedAppUsageList, appUsageList)
    }

    @Test
    fun test_request_permissions() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(
            GetFilteredAndSortedAppUsages(
                ProhibitedAppUsageRepository(),
                MockPackageNameRepository()
            )
        )

        // Input
        viewModel.reload()

        // Check output
        val isRequestedPermissions = viewModel.requestingPermission.first()
        assertTrue(isRequestedPermissions)
    }

    @Test
    fun test_other_errors() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(
            GetFilteredAndSortedAppUsages(
                ErrorAppUsageRepository(),
                MockPackageNameRepository()
            )
        )

        // Input
        viewModel.reload()

        // Check output
        val isRequestedPermissions = viewModel.requestingPermission.first()
        assertFalse(isRequestedPermissions)
    }

    @Test
    fun test_isReloading_initial_and_final_state() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(
            GetFilteredAndSortedAppUsages(
                MockAppUsageRepository(),
                MockPackageNameRepository()
            )
        )

        // Check initial state: 初期状態はfalseであることを確認
        assertFalse(viewModel.isReloading.value)

        // Input: reload()を実行して完了を待つ
        viewModel.reload()
        viewModel.appUsageList.first() // reload()の完了を待つ

        // Check completed state: 完了後はfalseに戻ることを確認
        assertFalse(viewModel.isReloading.value)
    }
}
