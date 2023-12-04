package lihan.chen.wordpressahan.feature.toolbar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarScreen() {
    Scaffold(
        topBar = {
        },
        content = {
            Column {
                CollapsingLayout(
                    modifier = Modifier.fillMaxWidth(),
                    collapsingTop = {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Color.Red))

                    },
                    bodyContent = {
                        Column {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(Color.Green)
                            )
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ){
                                items(50){
                                    Text(text = "User $it" , modifier = Modifier.padding(16.dp))
                                }
                            }
                        }

                    }
                )

            }

        },
        bottomBar = {}
    )

}

@Composable
private fun CollapsingLayout(
    modifier: Modifier = Modifier,
    collapsingTop : @Composable BoxScope.() -> Unit,
    bodyContent : @Composable BoxScope.() ->Unit
){
    var collapsingTopHeight by remember {
        mutableFloatStateOf(0f)
    }
    var offset by remember {
        mutableFloatStateOf(0f)
    }
    fun calculateOffset(delta: Float): Offset {
        val oldOffset = offset
        val newOffset = (oldOffset + delta).coerceIn(-collapsingTopHeight, 0f)
        offset = newOffset
        return Offset(0f, newOffset - oldOffset)
    }
    val nestedScrollConnection = remember {

    object : NestedScrollConnection {
        override fun onPreScroll(
            available: Offset,
            source: NestedScrollSource): Offset {
            return  when {
                available.y >= 0 -> Offset.Zero
                offset == -collapsingTopHeight -> Offset.Zero
                else -> calculateOffset(available.y)
            }
        }
        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset {
            return when {
                available.y <= 0 -> Offset.Zero
                offset == 0f -> Offset.Zero
                else -> calculateOffset(available.y)
            }
        }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) {
        Box(
            modifier = Modifier
                .onSizeChanged { size ->
                    collapsingTopHeight = size.height.toFloat()
                }
                .offset { IntOffset(x = 0, y = offset.roundToInt()) },
            content = collapsingTop,
        )
        Box(
            modifier = Modifier.offset {
                IntOffset(
                    x = 0,
                    y = (collapsingTopHeight + offset).roundToInt()
                )
            },
            content = bodyContent,
        )
    }


}




@Preview
@Composable
fun ToolBarScreenPreview() {
    ToolBarScreen()

}