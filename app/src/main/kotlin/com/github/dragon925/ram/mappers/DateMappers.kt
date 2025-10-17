package com.github.dragon925.ram.mappers

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.alternativeParsing
import kotlinx.datetime.format.char

private val dateTimeFormat = DateTimeComponents.Format {
    date(LocalDate.Formats.ISO)
    char('T')
    time(LocalTime.Formats.ISO)
    alternativeParsing({}) {
        char('.')
        secondFraction(3)
    }
    offset(UtcOffset.Formats.ISO)
}

fun parseDateTime(date: String): LocalDateTime = dateTimeFormat.parse(date).toLocalDateTime()