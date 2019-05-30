package com.vitantonio.nagauzzi.unusedappfinder.extension

import android.content.Context

fun Int.getString(context: Context): String = context.getString(this)
