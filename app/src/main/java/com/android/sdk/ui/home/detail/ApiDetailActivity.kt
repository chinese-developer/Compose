package com.android.sdk.ui.home.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.sdk.data.ApiDataProvider
import com.android.sdk.theme.ComposeSdkTheme
import com.android.sdk.ui.home.detail.entities.ApiDetail

class ApiDetailActivity : ComponentActivity() {

    private val platformType: String by lazy {
        intent?.getStringExtra(TYPE) ?: PlatformType.XJ.name
    }

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSdkTheme {
                ApiDetailScreen(ApiDetail(platformType, "https://test.com/")) {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        const val TYPE = "type"
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, listViewType: String, isDarkTheme: Boolean) =
            Intent(context, ApiDetailActivity::class.java).apply {
                putExtra(TYPE, listViewType)
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val apiDetail = ApiDataProvider.apiDetail
    ComposeSdkTheme {
        ApiDetailScreen(apiDetail) {}
    }
}