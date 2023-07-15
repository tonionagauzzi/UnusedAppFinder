package com.vitantonio.nagauzzi.unusedappfinder.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.extension.getString
import java.time.Instant

@Composable
fun AppUsageTextGroup(
    modifier: Modifier,
    name: String,
    lastUsedTime: Long,
    installedTime: Long,
) {
    Text(
        modifier = modifier
            .width(128.dp)
            .padding(top = 4.dp, bottom = 4.dp),
        fontSize = 16.sp,
        maxLines = 1,
        text = name,
        textAlign = TextAlign.Center
    )
    Text(
        modifier = modifier
            .width(128.dp)
            .padding(top = 2.dp),
        fontSize = 12.sp,
        maxLines = 1,
        text = stringResource(R.string.label_last_used_date) + stringResource(R.string.label_separator),
        textAlign = TextAlign.Center
    )
    Text(
        modifier = modifier.width(128.dp),
        fontSize = 12.sp,
        maxLines = 1,
        text = Instant.ofEpochMilli(lastUsedTime).getString("yyyy/MM/dd"),
        textAlign = TextAlign.Center
    )
    Text(
        modifier = modifier
            .width(128.dp)
            .padding(top = 2.dp),
        fontSize = 12.sp,
        maxLines = 1,
        text = stringResource(R.string.label_installed_date) + stringResource(R.string.label_separator),
        textAlign = TextAlign.Center
    )
    Text(
        modifier = modifier
            .width(128.dp)
            .padding(bottom = 12.dp),
        fontSize = 12.sp,
        maxLines = 1,
        text = Instant.ofEpochMilli(installedTime).getString("yyyy/MM/dd"),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun PreviewAppUsageTextGroup() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppUsageTextGroup(
            modifier = Modifier,
            name = "name0",
            installedTime = 0,
            lastUsedTime = 0
        )
    }
}
