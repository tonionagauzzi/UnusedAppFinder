package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.presenter.AppUsageListPresenter
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import java.util.*

class UnusedAppListViewModel(
    private val useCase: GetAppUsages
) : ViewModel(), AppUsageListPresenter {

    init {
        useCase.presenter = this
    }

    fun getAppUsages() = useCase.execute()

    override fun showAppUsageList(list: List<AppUsage>) {
        // TODO not implemented
        list.filter {
            it.enableUninstall
        }.map {
            Log.d("UnusedAppFinder", "${it.name} was unused since ${Date(it.lastUsedTime)}")
        }
    }

    override fun requirePermissionForAppUsage() {
        // TODO not implemented
        Log.d("UnusedAppFinder", "Required permission")
    }
}
