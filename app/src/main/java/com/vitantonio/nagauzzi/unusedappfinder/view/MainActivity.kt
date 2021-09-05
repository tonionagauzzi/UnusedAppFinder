package com.vitantonio.nagauzzi.unusedappfinder.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.vitantonio.nagauzzi.unusedappfinder.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(getToolBar())
    }
}

fun Activity.getToolBar() = this.findViewById(R.id.toolbar) as Toolbar

fun Activity.getSupportActionBar() = (this as MainActivity).supportActionBar
