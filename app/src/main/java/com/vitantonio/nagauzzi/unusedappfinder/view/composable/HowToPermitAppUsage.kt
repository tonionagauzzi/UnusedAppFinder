package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitantonio.nagauzzi.unusedappfinder.R

@Composable
fun HowToPermitAppUsage(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    onClick: () -> Unit = {
        context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
    },
) {
    Column(
        modifier = modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.description_how_to_permit_app_usage, stringResource(R.string.app_name_short))
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                disabledBackgroundColor = MaterialTheme.colors.primaryVariant
            ),
            onClick = onClick
        ) {
            Text(
                text = stringResource(id = R.string.button_permit_app_usage),
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewHowToPermitAppUsage() {
    HowToPermitAppUsage()
}
