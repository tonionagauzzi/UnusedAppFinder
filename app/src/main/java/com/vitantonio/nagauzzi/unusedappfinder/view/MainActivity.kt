package com.vitantonio.nagauzzi.unusedappfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.UnusedAppRoot
import com.vitantonio.nagauzzi.unusedappfinder.view.composable.UnusedAppTopBar
import com.vitantonio.nagauzzi.unusedappfinder.view.theme.UnusedAppListTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var getAppUsages: GetAppUsages

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
                    },
                ) { contentPadding ->
                    val coroutineScope = rememberCoroutineScope()
                    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh = {
                        coroutineScope.launch {
                            getAppUsages()
                        }
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

        CoroutineScope(Dispatchers.Default).launch {
            getAppUsages()
        }
    }
}
