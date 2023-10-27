package lihan.chen.wordpressahan.feature.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimationScreen() {
    val infiniteTransition = rememberInfiniteTransition(label = "rememberInfiniteTransition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                3000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    , label = ""
    )
    val rotation2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                3000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
        , label = ""
    )
    val rotation3 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                3000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
        , label = ""
    )
    val rotation4 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                3000,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
        , label = ""
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        DrawRectItem(
            easing = "FastOutSlowInEasing",
            degrees = rotation,
            color = Color.Yellow
        )
        Spacer(modifier = Modifier.height(16.dp))
        DrawRectItem(
            easing = "LinearEasing",
            degrees = rotation2,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        DrawRectItem(
            easing = "FastOutLinearInEasing",
            degrees = rotation3,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        DrawRectItem(
            easing = "LinearOutSlowInEasing",
            degrees = rotation4,
            color = Color.Green
        )
    }
}

@Composable
fun DrawRectItem(
    degrees : Float,
    color : Color,
    easing : String
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = easing , fontWeight = FontWeight.Bold , modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(36.dp))
        Canvas(modifier = Modifier.size(80.dp)){
            rotate(degrees = degrees){
                drawRect(
                    color = color
                )
            }
        }

    }
}


@Preview
@Composable
fun AnimationScreenPreview() {
    AnimationScreen()

}