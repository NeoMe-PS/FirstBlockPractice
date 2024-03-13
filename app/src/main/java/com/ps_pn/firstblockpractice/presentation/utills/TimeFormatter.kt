package com.ps_pn.firstblockpractice.presentation.utills

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

class TimeFormatter {

    companion object {
        private const val FULL_DATE_FORMAT = "MM dd,yyyy"
        private const val PERIOD_DATE_FORMAT = "MM.dd"

        @OptIn(FormatStringsInDatetimeFormats::class)
        private val fullDateTimeFormat = LocalDateTime.Format {
            byUnicodePattern(FULL_DATE_FORMAT)
        }

        @OptIn(FormatStringsInDatetimeFormats::class)
        private val periodTimeFormat = LocalDateTime.Format {
            byUnicodePattern(PERIOD_DATE_FORMAT)
        }

        fun formatFullDate(time: Long): String {
            return fullDateTimeFormat.format(
                Instant.fromEpochMilliseconds(time)
                    .toLocalDateTime(TimeZone.currentSystemDefault())
            )
        }
        fun formatPeriodDate(time: Long): String {
            return periodTimeFormat.format(
                Instant.fromEpochMilliseconds(time)
                    .toLocalDateTime(TimeZone.currentSystemDefault())
            )
        }
    }
}