package com.plcoding.bookpedia.core.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// https://github.com/Kotlin/kotlinx-datetime
@Composable
fun RealTimeClock(
    timeZone: TimeZone = TimeZone.of("Asia/Shanghai"),
    fontSize: TextUnit = 30.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
) {
    val timeText = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        while (true) {
            val now = Clock.System.now()
            val beijingTime = now.toLocalDateTime(timeZone)
            timeText.value = beijingTime.formatTime()
            delay(1000)
        }
    }
    Text(
        text = timeText.value,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color
    )
}

private fun LocalDateTime.formatTime(): String = buildString {
    append(hour.toString().padStart(2, '0'))
    append(':')
    append(minute.toString().padStart(2, '0'))
    append(':')
    append(second.toString().padStart(2, '0'))
}
