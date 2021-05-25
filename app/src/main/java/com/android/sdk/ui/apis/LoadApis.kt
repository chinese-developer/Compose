package com.android.sdk.ui.apis

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.sdk.theme.purple
import com.android.sdk.theme.purple200
import com.android.sdk.theme.typography

@Composable
fun LoadApis(
    api: String,
    XJOnclick: () -> Unit,
    IMOnclick: () -> Unit
) {
    var draw by remember { mutableStateOf(false) }

    val outlineButtonColor = ButtonDefaults.outlinedButtonColors(
        contentColor = purple200,
    )
    val mainButtonColor = ButtonDefaults.buttonColors(
        backgroundColor = purple,
        contentColor = MaterialTheme.colors.surface
    )

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {

        OutlinedButton(
            colors = outlineButtonColor,
            onClick = { IMOnclick() },
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw) 30f else 5f).value,
                    translationX = animateFloatAsState(if (draw) 320f else 0f).value,
                    translationY = 0f,
                )
        ) {
            Text(text = "IM", modifier = Modifier.padding(8.dp))
        }

        OutlinedButton(
            onClick = { XJOnclick() },
            colors = mainButtonColor,
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw) 30f else 10f).value,
                    translationX = animateFloatAsState(if (draw) -320f else 0f).value,
                    translationY = animateFloatAsState(if (draw) 0f else 30f).value
                )
        ) {
            Text(text = "XJ", modifier = Modifier.padding(8.dp))
        }

        Button(
            onClick = { draw = !draw },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
                .size(150.dp)
                .graphicsLayer(
                    shadowElevation = animateFloatAsState(if (draw) 30f else 5f).value,
                    translationY = animateFloatAsState(if (draw) 0f else 50f).value
                )
        ) {
            Text(
                text = api,
                style = typography.h6.copy(fontSize = 16.sp),
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.padding(30.dp))

    }
}