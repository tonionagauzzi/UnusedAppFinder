package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

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
