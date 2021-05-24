package com.android.sdk.ui.home.lists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.android.sdk.theme.ComposeSdkTheme

class ListViewActivity : ComponentActivity() {

  private val listType: String by lazy {
    intent?.getStringExtra(TYPE) ?: ListViewType.VERTICAL.name
  }

  private val isDarkTheme: Boolean by lazy {
    intent?.getBooleanExtra(DARK_THEME, false) ?: false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      BaseView(isDarkTheme = isDarkTheme) {

      }
    }
  }

  companion object {
    const val TYPE = "type"
    const val DARK_THEME = "darkTheme"
    fun newIntent(context: Context, listViewType: String, isDarkTheme: Boolean) =
      Intent(context, ListViewActivity::class.java).apply {
        putExtra(TYPE, listViewType)
        putExtra(DARK_THEME, isDarkTheme)
      }
  }
}

@Composable
fun BaseView(isDarkTheme: Boolean, content: @Composable() () -> Unit) {
  ComposeSdkTheme(isDarkTheme) {
    content()
  }
}
