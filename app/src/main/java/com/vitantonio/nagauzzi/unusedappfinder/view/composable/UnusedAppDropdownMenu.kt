package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.vitantonio.nagauzzi.unusedappfinder.R

@Composable
fun UnusedAppDropdownMenu(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    isMenuExpanded: Boolean,
    onClickAboutThisApp: () -> Unit = {
        context.startActivity(
            Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("market://details?id=${context.packageName}")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    },
    onClickOpenSourceLicenses: () -> Unit = {
        context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
    },
    onDismiss: () -> Unit,
) {
    DropdownMenu(
        expanded = isMenuExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier,
        offset = DpOffset(0.dp, 0.dp),
        properties = PopupProperties(focusable = true),
        content = {
            DropdownMenuItem(
                onClick = {
                    onClickAboutThisApp()
                    onDismiss()
                },
                text = {
                    Text(text = stringResource(id = R.string.label_about))
                }
            )
            DropdownMenuItem(
                onClick = {
                    onClickOpenSourceLicenses()
                    onDismiss()
                },
                text = {
                    Text(text = stringResource(id = R.string.label_oss_license))
                }
            )
        }
    )
}
