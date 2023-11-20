package lihan.chen.wordpressahan.feature.datepicker

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Date

@ExperimentalMaterial3Api
@Composable
fun DatePickerScreen() {

    var isShowDialog by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                isShowDialog = true
            }) {
                Text(text = "Date Pick")
            }
            Text(text = selectedDate)

        }
        if (isShowDialog){
            DatePickerDialog(
                onDismiss = {
                    isShowDialog = false
                },
                onConfirm = {
                    selectedDate = it
                }
            )
        }

    }


}

@ExperimentalMaterial3Api
@Composable
private fun DatePickerDialog(
    onDismiss : () -> Unit = {},
    onConfirm : (String) -> Unit = {}
){
    val datePickerState = rememberDatePickerState()
    androidx.compose.material3.DatePickerDialog(
        onDismissRequest = { onDismiss()},
        confirmButton = {
            Button(onClick = {
                datePickerState.selectedDateMillis?.let {
                    onConfirm(convertDate(it))
                }
                onDismiss()
            }) {
                Text(text = "Done")
            }

        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }

    ) {
        DatePicker(
            state = datePickerState
        )
    }

}

private fun convertDate(time : Long) : String{
    if (time == 0L) return ""
   return SimpleDateFormat("yyyy-MM-dd").format(Date(time))
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun DatePickerScreenPreview() {
    DatePickerScreen()

}