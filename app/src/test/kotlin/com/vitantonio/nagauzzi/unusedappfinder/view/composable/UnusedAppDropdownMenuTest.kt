package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.vitantonio.nagauzzi.unusedappfinder.R
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class UnusedAppDropdownMenuTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_UnusedAppDropdownMenu() {
        // Initialize
        val context: Context = RuntimeEnvironment.getApplication()

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

        // Check Output
        composeTestRule.onNodeWithText(context.getString(R.string.label_about)).performClick()
        assertEquals(1, clickAboutThisAppCount)
        composeTestRule.onNodeWithText(context.getString(R.string.label_oss_license)).performClick()
        assertEquals(1, clickOpenSourceLicenses)
    }
}
