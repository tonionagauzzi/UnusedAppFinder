package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.presenter.AppUsageListPresenter
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages

class UnusedAppListViewModel(
    private val useCase: GetAppUsages
) : ViewModel(), AppUsageListPresenter {

    val appUsageList = MutableLiveData<List<AppUsage>>()

    init {
        useCase.presenter = this
    }

    fun getAppUsages() = useCase.execute()

    override fun showAppUsageList(list: List<AppUsage>) {
        appUsageList.value = list.filter {
            it.enableUninstall
        }.sortedByDescending {
            it.lastUsedTime
        }
    }

    override fun requirePermissionForAppUsage() {
        // TODO not implemented
        Log.d("UnusedAppFinder", "Required permission")
    }
}
