package com.android.sdk.ui.home.detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.sdk.data.ApiDataProvider
import com.android.sdk.theme.green700
import com.android.sdk.theme.typography
import com.android.sdk.ui.home.detail.entities.ApiDetail
import com.android.sdk.ui.utils.horizontalGradientBackground

@Composable
fun ApiDetailScreen(apiDetail: ApiDetail, onBack: () -> Unit) {
    val surfaceGradient = ApiDataProvider.defaultSurfaceGradient(isSystemInDarkTheme())
    Scaffold(
        bottomBar = { ApiDetailBottomBar(onBack) },
        floatingActionButton = { ApiFloatActionButton() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Box(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            val scrollState = rememberScrollState(0)
            ApiDetailTopSection(apiDetail, scrollState)
            StatisticsSection(apiDetail)
            ResponseSection(apiDetail)
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun ResponseSection(apiDetail: ApiDetail) {
    Text(
        text = "Response",
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        style = typography.h5
    )
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = "Waiting..", style = typography.subtitle2)
    }
}

@Composable
fun StatisticsSection(apiDetail: ApiDetail) {
    val valueModifier = Modifier.padding(bottom = 16.dp, top = 4.dp)
    Text(
        text = "Statistics",
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        style = typography.h5
    )
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Host", style = typography.subtitle2)
                Text(text = "", modifier = valueModifier)
                Text(text = "Request", style = typography.subtitle2)
                Text(text = "", modifier = valueModifier)
                Text(text = "Headers", style = typography.subtitle2)
                Text(text = "", modifier = valueModifier)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Params", style = typography.subtitle2)
                Text(text = "", modifier = valueModifier)
                Text(text = "Status", style = typography.subtitle2)
                Text(text = "", modifier = valueModifier)
                Text(text = "Error", style = typography.subtitle2)
                Text(text = "", modifier = valueModifier)
            }
        }
    }
}

@Composable
fun ApiDetailTopSection(apiDetail: ApiDetail, scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .alpha(
                animateFloatAsState(
                    (1 - scrollState.value / 150)
                        .coerceIn(0, 1)
                        .toFloat()
                ).value
            )
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = apiDetail.platform,
            style = typography.h6.copy(fontWeight = FontWeight.ExtraBold)
        )
        Text(
            text = apiDetail.platform,
            color = green700
        )
    }
}

@Composable
fun ApiFloatActionButton() {
    var pressed by remember { mutableStateOf(false) }
    ExtendedFloatingActionButton(
        icon = { Icon(Icons.Default.Send, contentDescription = null) },
        text = { Text(text = "Request Api") },
        onClick = { pressed = !pressed },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.width(animateDpAsState(if (pressed) 200.dp else 120.dp).value)
    )
}

@Composable
fun ApiDetailBottomBar(onBack: () -> Unit) {
    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }
        IconButton(onClick = {}) {
            Icon(Icons.Default.MoreVert, contentDescription = null)
        }
    }
}