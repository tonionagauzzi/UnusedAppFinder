package com.vitantonio.nagauzzi.unusedappfinder.extension

import android.content.Context
import com.vitantonio.nagauzzi.unusedappfinder.R
import java.text.SimpleDateFormat
import java.util.*

const val millisecondsOn1Second = 1000
const val millisecondsOn1Minute = millisecondsOn1Second * 60
const val millisecondsOn1Hour = millisecondsOn1Minute * 60
const val millisecondsOn1Day = millisecondsOn1Hour * 24

fun Date.changeMillisecond(millisecond: Int): Date {
    val date = this.clone() as Date
    date.time += millisecond
    return date
}

fun Date.changeDay(day: Int): Date {
    val date = this.clone() as Date
    date.time += day * millisecondsOn1Day
    return date
}

fun Date.changeMonth(adjustMonth: Int, calendar: Calendar = Calendar.getInstance()): Date {
    calendar.time = this.clone() as Date
    val newMonth = calendar.get(Calendar.MONTH) + adjustMonth
    if (newMonth >= 0) {
        calendar.set(Calendar.MONTH, newMonth)
    } else {
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1)
        calendar.set(Calendar.MONTH, (newMonth + 12) % 12)
    }
    return calendar.time
}

fun Long.toDate() = Date(this)

fun Date.getString(format: String = "yyyy/MM/dd") = SimpleDateFormat(format, Locale.JAPAN).format(this)!!

fun Date.toLastUsedDateString(context: Context, format: String = R.string.format_date.getString(context)) =
    if (time > 0) {
        "${R.string.label_last_used_date.getString(context)} " +
                "${R.string.label_separator.getString(context)} \n" +
                getString(format)
    } else {
        "${R.string.label_last_used_date.getString(context)} " +
                "${R.string.label_separator.getString(context)} \n" +
                R.string.label_unknown.getString(context)
    }

fun Date.toInstalledDateString(context: Context, format: String = R.string.format_date.getString(context)) =
    if (time > 0) {
        "${R.string.label_installed_date.getString(context)} " +
                "${R.string.label_separator.getString(context)} \n" +
                getString(format)
    } else {
        "${R.string.label_installed_date.getString(context)} " +
                "${R.string.label_separator.getString(context)} \n" +
                R.string.label_unknown.getString(context)
    }

fun Date.resetDateToZeroOClock(calendar: Calendar = Calendar.getInstance()): Date {
    calendar.time = this.clone() as Date
    calendar.set(Calendar.AM_PM, Calendar.AM)
    calendar.set(Calendar.HOUR, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    this.time = calendar.timeInMillis

    return this
}

fun Date.resetDateToStartDayOfMonth(calendar: Calendar = Calendar.getInstance()): Date {
    calendar.time = this.clone() as Date
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    this.time = calendar.timeInMillis

    return this
}
