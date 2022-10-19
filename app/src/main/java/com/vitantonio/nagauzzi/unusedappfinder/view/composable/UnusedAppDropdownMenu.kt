package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import android.content.Intent
import android.net.Uri
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
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
    modifier: Modifier,
    isMenuExpanded: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    DropdownMenu(
        expanded = isMenuExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier,
        offset = DpOffset(0.dp, 0.dp),
        properties = PopupProperties(focusable = true),
        content = {
            DropdownMenuItem(onClick = {
                context.startActivity(Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("market://details?id=${context.packageName}")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
                onDismiss()
            }) {
                Text(text = stringResource(id = R.string.label_about))
            }
            DropdownMenuItem(onClick = {
                context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
                onDismiss()
            }) {
                Text(text = stringResource(id = R.string.label_oss_license))
            }
        }
    )
}
