package lihan.chen.wordpressahan.feature.customdatepicker

import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale

@Composable
fun CustomDatePicker(
    modifier : Modifier = Modifier

) {
    var now by remember {
        mutableStateOf(
            Calendar.getInstance().apply {
                set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            }
        )
    }
    val dateTitles by remember {
        mutableStateOf(listOf(
            "SUN","MON","TUE","WED","THU","FRI","SAT"
        ))
    }
    var counter by remember {
        mutableIntStateOf(0)
    }
    var selected by remember {
        mutableIntStateOf(0)
    }

    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = (now.clone() as Calendar)
    val dates = arrayListOf<String>()
        for (i in 0 until 7) {
            dates.add(sdf.format(currentDate.time))
            currentDate.roll(Calendar.DAY_OF_MONTH, 1)
         }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val localDate = sdf.format(Calendar.getInstance().time)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dates.forEachIndexed { index, date ->
                Box(modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(8.dp),
                        color = if (selected == index) Color.Red.copy(alpha = 0.2f) else Color.White
                    )
                    .clip(RoundedCornerShape(8.dp))
                ){
                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                selected = index
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = dateTitles[index])
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = date.split("-")[2],
                            color = if (localDate == date ) Color.Red else Color.Black
                        )
                    }

                }
            }

        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                counter -=1
                now = Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                    add(Calendar.WEEK_OF_MONTH,counter)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = {
                counter +=1
                now = Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                    add(Calendar.WEEK_OF_MONTH,counter)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }


        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Selected : ${dates[selected]}")
    }

}

private fun getDateString(date: Date) : String {
    return SimpleDateFormat("yyyy-MM-dd").format(date)
}

@Preview(showSystemUi = true)
@Composable
fun CustomDatePickerPreview() {
    CustomDatePicker(
    )

}