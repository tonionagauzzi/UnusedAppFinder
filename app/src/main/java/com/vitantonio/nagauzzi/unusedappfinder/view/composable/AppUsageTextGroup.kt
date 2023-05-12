package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.extension.getString
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import java.time.Instant

@Composable
fun AppUsageTextGroup(
    modifier: Modifier,
    unusedApp: AppUsage,
) {
    Text(
        modifier = modifier
            .width(128.dp)
            .padding(top = 4.dp, bottom = 4.dp),
        fontSize = 16.sp,
        maxLines = 1,
        text = unusedApp.name,
        textAlign = TextAlign.Center,
    )
    Text(
        modifier = modifier
            .width(128.dp)
            .padding(top = 2.dp),
        fontSize = 12.sp,
        maxLines = 1,
        text = stringResource(R.string.label_last_used_date) + stringResource(R.string.label_separator),
        textAlign = TextAlign.Center,
    )
    Text(
        modifier = modifier.width(128.dp),
        fontSize = 12.sp,
        maxLines = 1,
        text = Instant.ofEpochMilli(unusedApp.lastUsedTime).getString("yyyy/MM/dd"),
        textAlign = TextAlign.Center,
    )
    Text(
        modifier = modifier
            .width(128.dp)
            .padding(top = 2.dp),
        fontSize = 12.sp,
        maxLines = 1,
        text = stringResource(R.string.label_installed_date) + stringResource(R.string.label_separator),
        textAlign = TextAlign.Center,
    )
    Text(
        modifier = modifier
            .width(128.dp)
            .padding(bottom = 12.dp),
        fontSize = 12.sp,
        maxLines = 1,
        text = Instant.ofEpochMilli(unusedApp.installedTime).getString("yyyy/MM/dd"),
        textAlign = TextAlign.Center,
    )
}
