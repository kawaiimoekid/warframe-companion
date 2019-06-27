package com.kawaiistudios.warframecompanion.util.extension

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration

fun DateTime.toPrettyTimeDifference(): String {
    val difference = Duration(this, DateTime.now(DateTimeZone.UTC))
    return when {
        difference.standardDays > 0 -> "${difference.standardDays}d"
        difference.standardHours > 0 -> "${difference.standardHours}h"
        difference.standardMinutes > 0 -> "${difference.standardMinutes}m"
        difference.standardSeconds > 0 -> "${difference.standardSeconds}s"
        else -> "?"
    }
}