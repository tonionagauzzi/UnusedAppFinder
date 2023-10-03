package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class AppUsageTextGroupTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_AppUsageTextGroup() {
        // Input
        val appName = "AppName"
        composeTestRule.setContent {
            AppUsageTextGroup(
                modifier = Modifier,
                name = appName,
                lastUsedTime = 1560000000L, // 2019/06/08 22:20:00 (JST)
                installedTime = 1550000000L, // 2019/02/13 04:33:20 (JST)
            )
        }

        // Check Output
        composeTestRule.onNodeWithText(appName).assertIsDisplayed()
        // TODO: Add tests for lastUsedTime and installedTime.
    }
}
