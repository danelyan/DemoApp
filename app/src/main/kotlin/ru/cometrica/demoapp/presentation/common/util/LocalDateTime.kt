package ru.cometrica.demoapp.presentation.common.util

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM YYYY")

fun ZonedDateTime.toUiDateTime(): String = this.format(dateTimeFormatter)

object ZonedDateTimeUtil {

    fun parseUiDateTime(dateTime: String): ZonedDateTime = ZonedDateTime.parse(dateTime, dateTimeFormatter)
}

