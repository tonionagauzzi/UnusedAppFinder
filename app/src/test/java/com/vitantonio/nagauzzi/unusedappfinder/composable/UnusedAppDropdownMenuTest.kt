package com.vitantonio.nagauzzi.unusedappfinder.composable

import androidx.compose.ui.test.junit4.createComposeRule
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.UnusedAppDropdownMenu
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UnusedAppDropdownMenuTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_UnusedAppDropdownMenu() {
        // Input
        var clickAboutThisAppCount = 0
        var clickOpenSourceLicenses = 0
        var dismissCount = 0
        composeTestRule.setContent {
            UnusedAppDropdownMenu(
                isMenuExpanded = true,
                onClickAboutThisApp = { clickAboutThisAppCount++ },
                onClickOpenSourceLicenses = { clickOpenSourceLicenses++ },
                onDismiss = { dismissCount++ }
            )
        }

        // Log for debugging
        // FIXME: DropdownMenu cannot be tested because even the following log output fails.
        // composeTestRule.onRoot().printToLog("UnusedAppFinder")
    }
}
