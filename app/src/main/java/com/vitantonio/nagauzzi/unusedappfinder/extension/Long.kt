package com.vitantonio.nagauzzi.unusedappfinder.extension

import java.time.Instant

fun Long.asEpochMilli(): Instant = Instant.ofEpochMilli(this)
