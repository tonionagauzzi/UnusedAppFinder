package com.vitantonio.nagauzzi.unusedappfinder

import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

fun getKodeinInstance() = Kodein.lazy {
    bind<UnusedAppListViewModel>() with provider { UnusedAppListViewModel() }
}