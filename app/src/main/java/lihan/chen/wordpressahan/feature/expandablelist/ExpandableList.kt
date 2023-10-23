package lihan.chen.wordpressahan.feature.expandablelist

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


@Composable
fun ExpandableList(
    modifier: Modifier = Modifier,
    state : ExpandableState
) {
    val isExpandList = remember {
        mutableStateListOf<Boolean>().apply {
            addAll(List(state.data.keys.size) { false })
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ){
        LazyColumn{
            state.data.onEachIndexed { index, entry ->
                val isExpand = isExpandList[index]
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                isExpandList[index] = !isExpand
                            }
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = entry.key.toString(),
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = if (isExpand) Icons.Default.ArrowDropDown else Icons.Default.KeyboardArrowUp,
                            contentDescription = "expand icon"
                        )



                    }
                }

                if (isExpand){
                    items(entry.value){
                        AnimatedVisibility(visible = true,
                            ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                                .padding(16.dp),
                            ) {
                                Text(
                                    text = it.name
                                )

                            }
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun ExpandableListPreview() {
    ExpandableList(
        state = ExpandableState()
    )

}