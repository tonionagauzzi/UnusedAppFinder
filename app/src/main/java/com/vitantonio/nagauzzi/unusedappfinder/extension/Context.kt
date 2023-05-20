package com.vitantonio.nagauzzi.unusedappfinder.extension

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.vitantonio.nagauzzi.unusedappfinder.R

fun Context.getFakeIcon(): Drawable = AppCompatResources.getDrawable(
    this, R.drawable.ic_launcher_foreground
)!!
