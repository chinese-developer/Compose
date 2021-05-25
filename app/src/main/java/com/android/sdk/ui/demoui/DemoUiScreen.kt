package com.android.sdk.ui.demoui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.sdk.theme.AppThemeState
import com.android.sdk.theme.ComposeSdkTheme
import com.android.sdk.ui.apis.LoadApis

@Composable
fun DemoUiScreen(appThemeState: MutableState<AppThemeState>) {
    ComposeSdkTheme(darkTheme = appThemeState.value.darkTheme) {
        LazyColumn(
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}

@Composable
@Preview
fun DefaultPreview() {
}