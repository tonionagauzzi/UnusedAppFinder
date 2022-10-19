package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.vitantonio.nagauzzi.unusedappfinder.R

@Composable
fun HowToPermitAppUsage(
    modifier: Modifier,
) {
    val context = LocalContext.current

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.Transparent,
        ),
        onClick = {
            context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }
    ) {
        Image(
            modifier = modifier,
            contentDescription = stringResource(id = R.string.description_how_to_permit_app_usage),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.how_to_permit_app_usage),
        )
    }
}
