package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.view.theme.UnusedAppListTheme

@OptIn(ExperimentalMaterial3Api::class)
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
                    Icon(
                        painter = painterResource(R.drawable.more_vert),
                        contentDescription = "Edit text"
                    )
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
    UnusedAppListTheme {
        UnusedAppTopBar()
    }
}
