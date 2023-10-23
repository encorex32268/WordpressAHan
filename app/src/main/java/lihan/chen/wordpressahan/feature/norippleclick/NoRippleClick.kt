package lihan.chen.wordpressahan.feature.norippleclick

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoRippleClick() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            var count by remember {
                mutableStateOf(0)
            }
            Text(text = "Count : $count")
            // 1
//            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
//                IconButton(
//                    onClick = {
//                        count+=1
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.AddCircle,
//                        contentDescription = "Count Add",
//                        tint = Color.Blue
//                    )
//                }
//            }
            // 2
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = Modifier.noRippleClick {
                    count+=1
                },
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Count Add",
                tint = Color.Blue
            )
        }

    }

}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)
}

fun Modifier.noRippleClick(
    onClick : () -> Unit
) : Modifier = composed {
        clickable(
            indication = null,
            interactionSource = remember {
                MutableInteractionSource()
            },
            onClick = onClick
        )
    }
@Preview
@Composable
fun NoRippleClickPreview() {
    NoRippleClick()
}