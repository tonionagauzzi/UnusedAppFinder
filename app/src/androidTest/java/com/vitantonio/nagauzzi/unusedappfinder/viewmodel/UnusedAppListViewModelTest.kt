package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.model.equalsWithoutIcon
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.ErrorAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.MockPackageNameRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.mock.ProhibitedAppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4ClassRunner::class)
class UnusedAppListViewModelTest {
    // FIXME: Remove ignore and run "./gradlew connectedAndroidTest" on terminal
    @Ignore
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
        val expectedAppUsageList = context.dummyAppUsages()
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
