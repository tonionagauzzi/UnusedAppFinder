package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel

@Composable
fun UnusedAppList(
    modifier: Modifier,
    unusedAppListViewModel: UnusedAppListViewModel = viewModel(),
) {
    val context = LocalContext.current
    val showingList by unusedAppListViewModel.showingList.collectAsState(emptyList())

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(showingList) { showingItem ->
            Column(
                modifier = modifier.clickable {
                    context.startActivity(Intent().apply {
                        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        data = Uri.parse("package:${showingItem.packageName}")
                    })
                },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = modifier
                        .size(60.dp)
                        .padding(top = 8.dp, bottom = 4.dp),
                    contentDescription = showingItem.name,
                    painter = rememberDrawablePainter(showingItem.icon),
                )
                AppUsageTextGroup(
                    modifier = modifier,
                    name = showingItem.name,
                    lastUsedTime = showingItem.lastUsedTime,
                    installedTime = showingItem.installedTime
                )
            }
        }
    }
}

// FIXME: Composable内でHiltのインスタンスを取得する方法がわからないので、Previewを実現できていない。
// @Preview
// @Composable
// fun PreviewUnusedAppList() {
//     UnusedAppList(
//         modifier = Modifier,
//         unusedAppListViewModel = viewModel(),
//         getAppUsages =
//     )
// }
