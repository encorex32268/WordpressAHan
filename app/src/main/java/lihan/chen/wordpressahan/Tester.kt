package lihan.chen.wordpressahan

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

fun main() {
    val localtime = LocalDateTime.now().dayOfWeek
    println(localtime)

}