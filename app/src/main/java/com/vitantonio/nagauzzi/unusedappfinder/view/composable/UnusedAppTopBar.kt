package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUnusedAppTopBar() {
    UnusedAppTopBar()
}
