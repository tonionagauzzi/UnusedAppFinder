package com.vitantonio.nagauzzi.unusedappfinder.extension

import android.content.Context
import android.graphics.drawable.Drawable
import com.vitantonio.nagauzzi.unusedappfinder.R

fun Context.dummyIcon(): Drawable = getDrawable(R.drawable.ic_launcher_foreground)!!
