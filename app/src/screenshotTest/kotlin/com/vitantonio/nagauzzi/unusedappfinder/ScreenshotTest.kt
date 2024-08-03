package com.vitantonio.nagauzzi.unusedappfinder

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.PreviewAppUsageTextGroup
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.PreviewHowToPermitAppUsage
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.PreviewUnusedAppList
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.PreviewUnusedAppTopBar

class ScreenshotTest {
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun AppUsageTextGroupScreenshotTest() = PreviewAppUsageTextGroup()

    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun HowToPermitAppUsageScreenshotTest() = PreviewHowToPermitAppUsage()

    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun UnusedAppListScreenshotTest() = PreviewUnusedAppList()

    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun UnusedAppTopBarScreenshotTest() = PreviewUnusedAppTopBar()
}
