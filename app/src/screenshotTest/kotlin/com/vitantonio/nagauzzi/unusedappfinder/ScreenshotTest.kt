package com.vitantonio.nagauzzi.unusedappfinder

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.PreviewAppUsageTextGroup
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.PreviewHowToPermitAppUsage
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.PreviewUnusedAppList
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.PreviewUnusedAppTopBar

class ScreenshotTest {
    @Preview(showBackground = true)
    @Composable
    fun AppUsageTextGroupScreenshotTest() = PreviewAppUsageTextGroup()

    @Preview(showBackground = true)
    @Composable
    fun HowToPermitAppUsageScreenshotTest() = PreviewHowToPermitAppUsage()

    @Preview(showBackground = true)
    @Composable
    fun UnusedAppListScreenshotTest() = PreviewUnusedAppList()

    @Preview(showBackground = true)
    @Composable
    fun UnusedAppTopBarScreenshotTest() = PreviewUnusedAppTopBar()
}
