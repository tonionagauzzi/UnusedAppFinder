package com.vitantonio.nagauzzi.unusedappfinder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.vitantonio.nagauzzi.unusedappfinder.extension.dummyAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.AppUsageTextGroup
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.HowToPermitAppUsage
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.UnusedAppStatelessList

class ScreenshotTest {
    @Preview(showBackground = true)
    @Composable
    fun PreviewHowToPermitAppUsage() {
        HowToPermitAppUsage()
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewAppUsageTextGroup() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppUsageTextGroup(
                modifier = Modifier,
                name = "name0",
                installedTime = 0,
                lastUsedTime = 0
            )
        }
    }

    @Preview
    @Composable
    fun PreviewUnusedAppList() {
        val context = LocalContext.current
        UnusedAppStatelessList(
            appUsageList = context.dummyAppUsages(useDummyIcon = true),
            onColumnClicked = {}
        )
    }
}
