package com.vitantonio.nagauzzi.unusedappfinder.extension

import java.util.*

fun Date.changeYear(adjustYear: Int, calendar: Calendar = Calendar.getInstance()): Date {
    calendar.time = this.clone() as Date
    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + adjustYear)
    return calendar.time
}