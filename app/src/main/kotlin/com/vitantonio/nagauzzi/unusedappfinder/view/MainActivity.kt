package com.vitantonio.nagauzzi.unusedappfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.UnusedAppRoot
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.UnusedAppTopBar
import com.vitantonio.nagauzzi.unusedappfinder.view.theme.UnusedAppListTheme
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val unusedAppListViewModel: UnusedAppListViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
                    val coroutineScope = rememberCoroutineScope()
                    val pullToRefreshState = rememberPullToRefreshState()
                    PullToRefreshBox(
                        modifier = modifier.padding(contentPadding),
                        isRefreshing = pullToRefreshState.isAnimating,
                        onRefresh = {
                            coroutineScope.launch {
                                unusedAppListViewModel.reload()
                                pullToRefreshState.animateToHidden()
                            }
                        }
                    ) {
                        UnusedAppRoot(modifier = modifier.fillMaxSize())
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            unusedAppListViewModel.reload()
        }
    }
}
