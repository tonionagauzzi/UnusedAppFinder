package com.vitantonio.nagauzzi.unusedappfinder

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepositoryImpl
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel
import org.kodein.di.DKodein
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun Application.getKodeinInstance() = Kodein.lazy {
    bind<Context>(tag = "context") with singleton { this@getKodeinInstance }
    bind<ViewModelProvider.Factory>() with singleton { KodeinViewModelFactory(dkodein) }
    bind<AppUsageRepository>() with provider { AppUsageRepositoryImpl(instance(tag = "context")) }
    bind<GetAppUsages>() with provider { GetAppUsages(instance()) }
    bindVM<UnusedAppListViewModel>() with provider { UnusedAppListViewModel(instance()) }
}

class KodeinViewModelFactory(
    private val injector: DKodein
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return injector.instance<ViewModel>(tag = modelClass.name) as T
    }
}

inline fun <reified T : ViewModel> Kodein.Builder.bindVM(overrides: Boolean? = null): Kodein.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.name, overrides)
}