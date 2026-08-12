package com.policyboss.customer.utils.extension

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

// Extending the Long class directly
fun Long.toUIDateString(pattern: String = "dd.MM.yy"): String {
    val instant = Instant.ofEpochMilli(this)
    val formatter = DateTimeFormatter.ofPattern(pattern)
        .withZone(ZoneId.systemDefault())

    return formatter.format(instant)
}