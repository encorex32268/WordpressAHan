package lihan.chen.wordpressahan.feature.tabhost

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@ExperimentalFoundationApi
@Composable
fun TabHostWordpress() {
    val tabs = listOf("Tab1","Tab2","Tab3","Tab4")
    var textSize by remember {
        mutableStateOf(Size.Zero)
    }
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size){
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    val density = LocalDensity.current
    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier.customTabIndicatorOffset(
                        currentTabPosition = it[pagerState.currentPage],
                        tabWidth = tabWidths[pagerState.currentPage]
                    ),
                    height = 2.dp
                )
            },
            tabs = {
                tabs.forEachIndexed { index, tabName ->
                    Tab(
                        modifier = Modifier.padding(8.dp),
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.scrollToPage(index)
                            }
                        },
                        interactionSource = object : MutableInteractionSource{
                            override val interactions: Flow<Interaction> = emptyFlow()
                            override suspend fun emit(interaction: Interaction){}
                            override fun tryEmit(interaction: Interaction) = true
                        }
                    ) {
                        Text(
                            text = tabName,
                            fontSize = 24.sp,
                            onTextLayout = {
                                tabWidths[index] = with(
                                    density
                                ){
                                    it.size.width.toDp()
                                }
                            }
                        )
                    }
                }
            }
        )
        HorizontalPager(pageCount = tabs.size , state = pagerState) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Page ${it + 1}", fontSize = 24.sp)
            }

        }

    }

}
private fun Modifier.customTabIndicatorOffset(
    currentTabPosition : TabPosition,
    tabWidth : Dp
) : Modifier = composed (
    inspectorInfo = debugInspectorInfo {
        name = "debugInspectorInfo"
        value = currentTabPosition
    }
){
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(
            durationMillis = 250,
            easing = FastOutSlowInEasing
        ), label = "currentTabWidth"
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth)/2),
        animationSpec = tween(
            durationMillis = 250,
            easing = FastOutSlowInEasing
        ), label = "indicatorOffset"
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}


@ExperimentalFoundationApi
@Preview(showSystemUi = true)
@Composable
fun TabHostWordpressPreview() {
    TabHostWordpress()

}