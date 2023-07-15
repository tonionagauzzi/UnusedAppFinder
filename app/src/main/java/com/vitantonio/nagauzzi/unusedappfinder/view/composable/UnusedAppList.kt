package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.vitantonio.nagauzzi.unusedappfinder.extension.getFakeIcon
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel

@Composable
fun UnusedAppList(
    modifier: Modifier,
    unusedAppListViewModel: UnusedAppListViewModel = viewModel(),
) {
    val context = LocalContext.current
    val showingList by unusedAppListViewModel.showingList.collectAsState(emptyList())

    UnusedAppStatelessList(
        modifier = modifier,
        appUsageList = showingList,
        onColumnClicked = { packageName ->
            context.startActivity(
                Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.parse("package:$packageName")
                }
            )
        }
    )
}

@Composable
fun UnusedAppStatelessList(
    modifier: Modifier = Modifier,
    appUsageList: List<AppUsage>,
    onColumnClicked: (packageName: String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(appUsageList) { appUsage ->
            Column(
                modifier = modifier.clickable {
                    onColumnClicked(appUsage.packageName)
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = modifier
                        .size(60.dp)
                        .padding(top = 8.dp, bottom = 4.dp),
                    contentDescription = appUsage.name,
                    painter = rememberDrawablePainter(appUsage.icon)
                )
                AppUsageTextGroup(
                    modifier = modifier,
                    name = appUsage.name,
                    lastUsedTime = appUsage.lastUsedTime,
                    installedTime = appUsage.installedTime
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewUnusedAppList() {
    val context = LocalContext.current
    UnusedAppStatelessList(
        appUsageList = listOf(
            AppUsage(
                name = "name0",
                packageName = "packageName0",
                activityName = "activityName0",
                icon = context.getFakeIcon(),
                installedTime = 0,
                lastUsedTime = 0,
                enableUninstall = true
            ),
            AppUsage(
                name = "name1",
                packageName = "packageName1",
                activityName = "activityName1",
                icon = context.getFakeIcon(),
                installedTime = 86400000,
                lastUsedTime = 86400000,
                enableUninstall = true
            ),
            AppUsage(
                name = "name2",
                packageName = "packageName2",
                activityName = "activityName2",
                icon = context.getFakeIcon(),
                installedTime = 172800000,
                lastUsedTime = 172800000,
                enableUninstall = true
            ),
            AppUsage(
                name = "name3",
                packageName = "packageName3",
                activityName = "activityName3",
                icon = context.getFakeIcon(),
                installedTime = 259200000,
                lastUsedTime = 259200000,
                enableUninstall = true
            ),
            AppUsage(
                name = "name4",
                packageName = "packageName4",
                activityName = "activityName4",
                icon = context.getFakeIcon(),
                installedTime = 345600000,
                lastUsedTime = 345600000,
                enableUninstall = true
            )
        ),
        onColumnClicked = {}
    )
}
