package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel

@Composable
fun UnusedAppRoot(
    modifier: Modifier,
    unusedAppListViewModel: UnusedAppListViewModel = viewModel()
) {
    val requestingPermission by unusedAppListViewModel.requestingPermission.collectAsState()
    if (requestingPermission) {
        HowToPermitAppUsage(modifier)
    } else {
        UnusedAppList(modifier)
    }
}
