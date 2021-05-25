package com.android.sdk.ui.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.android.sdk.R
import com.android.sdk.data.ApiDataProvider
import com.android.sdk.theme.AppThemeState
import com.android.sdk.theme.ColorPallet
import com.android.sdk.ui.apis.LoadApis
import com.android.sdk.ui.home.detail.ApiDetailActivity

@ExperimentalFoundationApi
@Composable
fun HomeScreen(appThemeState: MutableState<AppThemeState>) {
    val showMenu = remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.semantics { testTag = "Home Screen" },
        topBar = {
            TopAppBar(
                title = { Text(text = "Api Test") },
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
                   /* IconButton(onClick = { showMenu.value = !showMenu.value }) {
                        Icon(Icons.Default.Palette, contentDescription = null)
                    }*/
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
    val list = remember { ApiDataProvider.homeScreenListItems }
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
            LazyColumn(
                state = rememberLazyListState(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { Spacer(modifier = Modifier.padding(8.dp)) }
                item { LoadApis("Login Api", {}, {}) }
                item { Spacer(modifier = Modifier.padding(16.dp)) }
                item { LoadApis("EventList Api", {}, {}) }
                item { Spacer(modifier = Modifier.padding(16.dp)) }
                item { LoadApis("EventsAndMarkets Api", {}, {}) }
                item { Spacer(modifier = Modifier.padding(64.dp)) }
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
            ApiDetailActivity.newIntent(context, homeScreenItems.type.toUpperCase(), isDarkTheme)
        }
        else -> throw IllegalArgumentException("wrong type!!")
    }
    context.startActivity(intent)
}