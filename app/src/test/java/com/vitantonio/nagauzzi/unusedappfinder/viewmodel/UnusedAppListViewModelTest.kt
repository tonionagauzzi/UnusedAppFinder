package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.model.equalsWithoutIcon
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.ErrorAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockPackageNameRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.ProhibitedAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class UnusedAppListViewModelTest {
    @Test
    fun test_sort_showing_list() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(
            GetAppUsages(MockAppUsageRepository()),
            MockPackageNameRepository()
        )
        val context: Context = ApplicationProvider.getApplicationContext()

        // Input
        viewModel.reload()

        // Check output
        val expectedAppUsageList = context.dummyAppUsages(dummyIcon = mockk()).sortedByDescending {
            it.lastUsedTime
        }
        val showingList = viewModel.showingList.first()
        assertTrue(showingList.equalsWithoutIcon(expectedAppUsageList))
    }

    @Test
    fun test_request_permissions() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(
            GetAppUsages(ProhibitedAppUsageRepository()),
            MockPackageNameRepository()
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
            GetAppUsages(ErrorAppUsageRepository()),
            MockPackageNameRepository()
        )

        // Input
        viewModel.reload()

        // Check output
        val isRequestedPermissions = viewModel.requestingPermission.first()
        assertFalse(isRequestedPermissions)
    }
}
