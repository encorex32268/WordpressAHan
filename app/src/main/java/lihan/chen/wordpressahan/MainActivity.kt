package lihan.chen.wordpressahan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import lihan.chen.wordpressahan.feature.expandablelist.ExpandableList
import lihan.chen.wordpressahan.feature.expandablelist.ExpandableViewModel
import lihan.chen.wordpressahan.feature.expandablelist.User
import lihan.chen.wordpressahan.feature.expandablelist.UserGroup
import lihan.chen.wordpressahan.feature.norippleclick.NoRippleClick
import lihan.chen.wordpressahan.ui.theme.WordpressAHanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordpressAHanTheme {
                val viewModel = viewModels<ExpandableViewModel>()
                val state by viewModel.value.state.collectAsState()
                ExpandableList(state = state , modifier = Modifier.fillMaxSize())
            }
        }
    }
}
