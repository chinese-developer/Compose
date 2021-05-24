package com.android.sdk.ui.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.android.sdk.R
import com.android.sdk.data.DemoDataProvider
import com.android.sdk.theme.AppThemeState
import com.android.sdk.theme.ColorPallet
import com.android.sdk.ui.home.lists.ListViewActivity

@ExperimentalFoundationApi
@Composable
fun HomeScreen(appThemeState: MutableState<AppThemeState>) {
  val showMenu = remember { mutableStateOf(false) }
  Scaffold(
    modifier = Modifier.semantics { testTag = "Home Screen" },
    topBar = {
      TopAppBar(
        title = { Text(text = "Compose") },
        elevation = 8.dp,
        actions = {
          IconButton(onClick = {
            appThemeState.value = appThemeState.value.copy(
              darkTheme = !appThemeState.value.darkTheme
            )
          }) {
            Icon(
              painterResource(id = R.drawable.ic_sleep),
              contentDescription = null
            )
          }
          IconButton(onClick = { showMenu.value = !showMenu.value }) {
            Icon(Icons.Default.Palette, contentDescription = null)
          }
        }
      )
    },
    content = {
      HomeScreenContent(
        appThemeState.value.darkTheme, showMenu
      ) { newPalletSelected ->
        appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
        showMenu.value = false
      }
    }
  )
}

@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(
  isDarkTheme: Boolean,
  showMenu: MutableState<Boolean>,
  onPalletChange: (ColorPallet) -> Unit
) {
  val context = LocalContext.current
  val list = remember { DemoDataProvider.homeScreenListItems }
  val screenWidth = LocalConfiguration.current.screenWidthDp
  val isWiderScreen = screenWidth > 550 // Random number for now
  Box(modifier = Modifier.fillMaxWidth()) {
    if (isWiderScreen) {
      LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(8.dp)
      ) {
        items(
          items = list,
          itemContent = {
            HomeScreenListView(it, context, isDarkTheme, isWiderScreen)
          })
      }
    } else {
      LazyColumn() {

      }
    }
  }
}

@ExperimentalFoundationApi
@Composable
fun HomeScreenListView(
  homeScreenItems: HomeScreenItems,
  context: Context,
  isDarkTheme: Boolean,
  isWiderScreen: Boolean
) {
  if (isWiderScreen) {
    Card(
      modifier = Modifier
        .clickable { homeItemClicked(homeScreenItems, context, isDarkTheme) }
        .height(150.dp)
        .padding(8.dp),
      backgroundColor = MaterialTheme.colors.primary,
      shape = RoundedCornerShape(8.dp),
      elevation = 4.dp,
      contentColor = MaterialTheme.colors.onPrimary
    ) {
      Text(
        text = homeScreenItems.name,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.h6
      )
    }
  } else {

  }
}

fun homeItemClicked(homeScreenItems: HomeScreenItems, context: Context, isDarkTheme: Boolean) {
  val intent = when (homeScreenItems) {
    is HomeScreenItems.ListView -> {
      ListViewActivity.newIntent(context, homeScreenItems.type.toUpperCase(), isDarkTheme)
    }
    else -> throw IllegalArgumentException("wrong type!!")
  }
  context.startActivity(intent)
}