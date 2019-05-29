package com.vitantonio.nagauzzi.unusedappfinder

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

@Suppress("unused")
class UnusedAppFinder : Application(), KodeinAware {
    override val kodein: Kodein = getKodeinInstance()
}