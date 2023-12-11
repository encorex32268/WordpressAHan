package lihan.chen.wordpressahan.feature.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils

@Composable
fun ColorCheckScreen() {
    var color by remember {
        mutableStateOf(Color.Black)
    }
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(color = color)
            .clickable {
                color = Color(
                           red = kotlin.random.Random.nextInt(255),
                           blue = kotlin.random.Random.nextInt(255),
                           green = kotlin.random.Random.nextInt(255),
                       )
            }
        ,
        contentAlignment = Alignment.Center
    ){
        val light = ColorUtils.calculateLuminance(color.toArgb())
        Text(
            text = "Test" ,
            fontSize = 36.sp,
            color = if (light < 0.5) Color.White else Color.Black
        )
    }



}

@Preview
@Composable
fun ColorCheckScreenPreview() {
    ColorCheckScreen()

}