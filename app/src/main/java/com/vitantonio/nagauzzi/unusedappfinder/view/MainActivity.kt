package com.vitantonio.nagauzzi.unusedappfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.UnusedAppRoot
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.UnusedAppTopBar
import com.vitantonio.nagauzzi.unusedappfinder.view.theme.UnusedAppListTheme
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val unusedAppListViewModel: UnusedAppListViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isRefreshing by remember { mutableStateOf(false) }

            UnusedAppListTheme {
                val modifier = Modifier

                Scaffold(
                    modifier = modifier.fillMaxSize(),
                    topBar = {
                        UnusedAppTopBar(
                            modifier = modifier
                        )
                    }
                ) { contentPadding ->
                    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh = {
                        unusedAppListViewModel.reload()
                    })
                    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
                        UnusedAppRoot(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(contentPadding)
                        )
                        PullRefreshIndicator(
                            refreshing = isRefreshing,
                            state = pullRefreshState,
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        unusedAppListViewModel.reload()
    }
}
