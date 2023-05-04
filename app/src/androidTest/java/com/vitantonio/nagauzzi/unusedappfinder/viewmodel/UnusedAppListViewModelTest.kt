package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.model.equalsWithoutIcon
import com.vitantonio.nagauzzi.unusedappfinder.repository.MockPackageNameRepository
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Error
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4ClassRunner::class)
class UnusedAppListViewModelTest {
    @Test
    fun test_sort_showing_list() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(MockPackageNameRepository())
        val context: Context = ApplicationProvider.getApplicationContext()

        // Input
        val appUsageList = listOf(
            AppUsage(
                name = "name0",
                packageName = "packageName0",
                activityName = "activityName0",
                icon = getDrawable(context, R.drawable.ic_launcher_foreground)!!,
                installedTime = 0,
                lastUsedTime = 0,
                enableUninstall = true
            ),
            AppUsage(
                name = "name1",
                packageName = "packageName1",
                activityName = "activityName1",
                icon = getDrawable(context, R.drawable.ic_launcher_foreground)!!,
                installedTime = 2,
                lastUsedTime = 2,
                enableUninstall = true
            ),
            AppUsage(
                name = "name2",
                packageName = "packageName2",
                activityName = "activityName2",
                icon = getDrawable(context, R.drawable.ic_launcher_foreground)!!,
                installedTime = 1,
                lastUsedTime = 1,
                enableUninstall = true
            ),
        )
        AppUsageState.update(to = Success(appUsageList))

        // Check output
        val expectedAppUsageList = listOf(
            // Descending by last used time
            AppUsage(
                name = "name1",
                packageName = "packageName1",
                activityName = "activityName1",
                icon = getDrawable(context, R.drawable.ic_launcher_foreground)!!,
                installedTime = 2,
                lastUsedTime = 2,
                enableUninstall = true
            ),
            AppUsage(
                name = "name2",
                packageName = "packageName2",
                activityName = "activityName2",
                icon = getDrawable(context, R.drawable.ic_launcher_foreground)!!,
                installedTime = 1,
                lastUsedTime = 1,
                enableUninstall = true
            ),
            AppUsage(
                name = "name0",
                packageName = "packageName0",
                activityName = "activityName0",
                icon = getDrawable(context, R.drawable.ic_launcher_foreground)!!,
                installedTime = 0,
                lastUsedTime = 0,
                enableUninstall = true
            ),
        )
        val showingList = viewModel.showingList.first()
        assertTrue(showingList.equalsWithoutIcon(expectedAppUsageList))
    }

    @Test
    fun test_request_permissions() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(MockPackageNameRepository())

        // Input
        AppUsageState.update(to = Error(SecurityException("Dummy security exception")))

        // Check output
        val isRequestedPermissions = viewModel.requestingPermission.first()
        assertTrue(isRequestedPermissions)
    }

    @Test
    fun test_other_errors() = runTest {
        // Initialize
        val viewModel = UnusedAppListViewModel(MockPackageNameRepository())

        // Input
        AppUsageState.update(to = Error(IllegalArgumentException("Dummy illegal argument exception")))

        // Check output
        val job = launch {
            viewModel.requestingPermission.collect {
                fail("This flow shouldn't be collected.")
            }
        }
        delay(100)
        job.cancel()
    }
}