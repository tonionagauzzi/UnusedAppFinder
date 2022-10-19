package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vitantonio.nagauzzi.unusedappfinder.R

@Composable
fun UnusedAppTopBar(
    modifier: Modifier = Modifier,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    val toggleIsMenuExpanded = { isMenuExpanded = !isMenuExpanded }

    TopAppBar(
        modifier = modifier,
        actions = {
            IconButton(onClick = toggleIsMenuExpanded) {
                Box(modifier = Modifier.weight(1f)) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Edit text")
                    UnusedAppDropdownMenu(
                        modifier = modifier,
                        isMenuExpanded = isMenuExpanded,
                        onDismiss = toggleIsMenuExpanded
                    )
                }
            }
        },
        title = {
            Text(stringResource(id = R.string.app_name))
        },
    )
}
