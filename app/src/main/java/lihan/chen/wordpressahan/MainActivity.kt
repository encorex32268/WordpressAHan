package lihan.chen.wordpressahan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import lihan.chen.wordpressahan.feature.customdatepicker.CustomDatePicker
import lihan.chen.wordpressahan.feature.datepicker.ColorCheckScreen
import lihan.chen.wordpressahan.ui.theme.WordpressAHanTheme

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordpressAHanTheme {
                CustomDatePicker()
            }
        }
    }
}
