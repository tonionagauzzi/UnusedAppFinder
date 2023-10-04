package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyIcon
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
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
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4ClassRunner::class)
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
        val expectedAppUsageList = listOf(
            // Descending by last used time
            AppUsage(
                name = "name1",
                packageName = "packageName1",
                activityName = "activityName1",
                icon = context.dummyIcon(),
                installedTime = 2,
                lastUsedTime = 2,
                enableUninstall = true
            ),
            AppUsage(
                name = "name2",
                packageName = "packageName2",
                activityName = "activityName2",
                icon = context.dummyIcon(),
                installedTime = 1,
                lastUsedTime = 1,
                enableUninstall = true
            ),
            AppUsage(
                name = "name0",
                packageName = "packageName0",
                activityName = "activityName0",
                icon = context.dummyIcon(),
                installedTime = 0,
                lastUsedTime = 0,
                enableUninstall = true
            )
        )
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
