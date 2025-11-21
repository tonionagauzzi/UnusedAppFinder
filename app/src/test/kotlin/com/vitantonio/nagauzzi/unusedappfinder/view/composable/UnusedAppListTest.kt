package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.vitantonio.nagauzzi.unusedappfinder.extension.asEpochMilli
import com.vitantonio.nagauzzi.unusedappfinder.extension.getString
import com.vitantonio.nagauzzi.unusedappfinder.view.model.AppUsageUiModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UnusedAppListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_UnusedAppStatelessList() {
        // Input
        val appUsageList = AppUsageUiModel.dummyList()
        composeTestRule.setContent {
            UnusedAppStatelessList(
                modifier = Modifier,
                appUsageList = appUsageList,
                onColumnClicked = {}
            )
        }

        // Check Output
        appUsageList.forEach {
            val node = composeTestRule.onNodeWithText(it.name)
            node.assertIsDisplayed()
            node.assertTextContains(it.name)
            node.assertTextContains(it.lastUsedTime.asEpochMilli().getString("yyyy/MM/dd"))
            node.assertTextContains(it.installedTime.asEpochMilli().getString("yyyy/MM/dd"))
            node.assertHasClickAction()
        }
    }
}
