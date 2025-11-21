package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
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
    fun test_sort_showing_list() = runTest {
        // Initialize
        val context: Context = ApplicationProvider.getApplicationContext()
        val viewModel = UnusedAppListViewModel(
            context,
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
        val showingList = viewModel.showingList.first()
        assertEquals(expectedAppUsageList.size, showingList.size)
        expectedAppUsageList.zip(showingList).forEach { (expected, actual) ->
            assertEquals(expected.name, actual.name)
            assertEquals(expected.packageName, actual.packageName)
            assertEquals(expected.activityName, actual.activityName)
            assertEquals(expected.installedTime, actual.installedTime)
            assertEquals(expected.lastUsedTime, actual.lastUsedTime)
            assertEquals(expected.enableUninstall, actual.enableUninstall)
        }
    }

    @Test
    fun test_request_permissions() = runTest {
        // Initialize
        val context: Context = ApplicationProvider.getApplicationContext()
        val viewModel = UnusedAppListViewModel(
            context,
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
        val context: Context = ApplicationProvider.getApplicationContext()
        val viewModel = UnusedAppListViewModel(
            context,
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
}
