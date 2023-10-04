package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class HowToPermitAppUsageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_HowToPermitAppUsage() {
        // Input
        var clickCount = 0
        composeTestRule.setContent {
            HowToPermitAppUsage(onClick = { clickCount++ })
        }

        // Check Output
        val matcher = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button)
        val node = composeTestRule.onNode(matcher = matcher)
        node.assertIsDisplayed()
        node.assertHasClickAction()
        node.performClick()
        assertEquals(1, clickCount)
    }
}
