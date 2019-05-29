package com.vitantonio.nagauzzi.unusedappfinder

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class UnusedAppFinder(override val kodein: Kodein = getKodeinInstance()) : Application(), KodeinAware