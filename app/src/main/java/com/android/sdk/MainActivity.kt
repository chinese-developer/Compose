package com.android.sdk

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.android.sdk.R.string
import com.android.sdk.theme.AppThemeState
import com.android.sdk.theme.ColorPallet
import com.android.sdk.theme.ComposeSdkTheme
import com.android.sdk.theme.SystemUiController
import com.android.sdk.theme.blue700
import com.android.sdk.theme.green700
import com.android.sdk.theme.orange700
import com.android.sdk.theme.purple700
import com.android.sdk.ui.home.HomeScreen

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
  val bottomNavBarContentDescription = stringResource(id = string.a11y_bottom_navigation_bar)

  Column {

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
       }
    }
  }
}