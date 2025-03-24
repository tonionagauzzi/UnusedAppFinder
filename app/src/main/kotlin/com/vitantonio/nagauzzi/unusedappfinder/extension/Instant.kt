package com.vitantonio.nagauzzi.unusedappfinder.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun Instant.minusMonths(months: Long): Instant = atZone(ZoneOffset.UTC)
    .minusMonths(months)
    .toInstant()

fun Instant.resetTimeToStartOfDay(): Instant = atZone(ZoneOffset.UTC)
    .withHour(0)
    .withMinute(0)
    .withSecond(0)
    .withNano(0)
    .toInstant()

fun Instant.resetTimeToEndOfDay(): Instant = atZone(ZoneOffset.UTC)
    .withHour(0)
    .withMinute(0)
    .withSecond(0)
    .withNano(0)
    .plus(1, ChronoUnit.DAYS)
    .minus(1, ChronoUnit.MILLIS)
    .toInstant()

fun Instant.resetDateToStartDayOfMonth(): Instant = atZone(ZoneOffset.UTC)
    .withDayOfMonth(1)
    .toInstant()

fun Instant.getString(pattern: String): String = DateTimeFormatter.ofPattern(pattern)
    .format(LocalDateTime.ofInstant(this, ZoneId.systemDefault()))
