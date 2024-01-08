package com.ngr.common.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

private const val DATE_FORMATTED = "dd/MM/yy - HH:mm"

fun formatDate(stringDate: String): Date {
    val instant  = Instant.parse(stringDate)
    return Date.from(instant)
}

fun getFormattedDate(stringDate: String): String {
    val date = formatDate(stringDate)
    val sdf = SimpleDateFormat(DATE_FORMATTED, Locale.getDefault())
    return sdf.format(date)
}
