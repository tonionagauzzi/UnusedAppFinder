package com.vitantonio.nagauzzi.unusedappfinder

import android.app.Application
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepositoryImpl
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.WebViewViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(applicationContext)
            modules(module)
        }
    }

    val module = module {
        single { AppUsageRepositoryImpl(androidContext()) as AppUsageRepository }
        single { GetAppUsages(get()) }
        viewModel { WebViewViewModel() }
        viewModel { UnusedAppListViewModel(get(), get()) }
    }
}