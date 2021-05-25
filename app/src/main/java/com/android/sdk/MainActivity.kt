package com.android.sdk

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.sdk.R.string
import com.android.sdk.theme.AppThemeState
import com.android.sdk.theme.ColorPallet
import com.android.sdk.theme.ComposeSdkTheme
import com.android.sdk.theme.SystemUiController
import com.android.sdk.theme.blue700
import com.android.sdk.theme.green700
import com.android.sdk.theme.orange700
import com.android.sdk.theme.purple700
import com.android.sdk.ui.demoui.DemoUiScreen
import com.android.sdk.ui.home.HomeScreen
import com.android.sdk.ui.utils.RotateIcon
import com.guru.fontawesomecomposelib.FaIcon

class MainActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = remember { SystemUiController(window) }
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            BaseView(appTheme.value, systemUiController) {
                MainAppContent(appTheme)
            }
        }
    }

    @Composable
    fun BaseView(
        appThemeState: AppThemeState,
        systemUiController: SystemUiController?,
        content: @Composable () -> Unit
    ) {
        val color = when (appThemeState.pallet) {
            ColorPallet.GREEN -> green700
            ColorPallet.BLUE -> blue700
            ColorPallet.ORANGE -> orange700
            ColorPallet.PURPLE -> purple700
        }

        systemUiController?.setStatusBarColor(color, darkIcons = appThemeState.darkTheme)
        ComposeSdkTheme(darkTheme = appThemeState.darkTheme, colorPallet = appThemeState.pallet) {
            content()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainAppContent(appThemeState: MutableState<AppThemeState>) {
    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavType.HOME) }

    Column {
        HomeScreenContent(
            homeScreen = homeScreenState.value,
            appThemeState = appThemeState,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationContent(
            modifier = Modifier.testTag("bottom_navigation_bar"),
            homeScreenState = homeScreenState
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            when (screen) {
                BottomNavType.HOME -> HomeScreen(appThemeState)
                BottomNavType.DEMOUI -> DemoUiScreen(appThemeState)
            }
        }
    }
}


@Composable
fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.Home,
                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
            },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = { Text(text = stringResource(id = string.navigation_item_apis)) },
        )

        BottomNavigationItem(
            icon = {
                RotateIcon(
                    state = animate,
                    asset = Icons.Default.PlayArrow,
                    angle = 720f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onClick = {
                homeScreenState.value = BottomNavType.DEMOUI
                animate = true
            },
            label = { Text(text = stringResource(id = string.navigation_item_demoui)) }
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    ComposeSdkTheme {
        Box(modifier = Modifier.size(150.dp)) {

        }
    }
}