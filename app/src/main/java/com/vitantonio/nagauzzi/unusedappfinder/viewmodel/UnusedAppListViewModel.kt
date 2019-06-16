package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.presenter.AppUsageListPresenter
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages

class UnusedAppListViewModel(
    private val packageName: String,
    private val useCase: GetAppUsages
) : ViewModel(), AppUsageListPresenter {

    val appUsageList = MutableLiveData<List<AppUsage>>()
    val requestingPermission = MutableLiveData<Boolean>()

    init {
        useCase.presenter = this
    }

    fun getAppUsages() {
        requestingPermission.value = false
        useCase.execute()
    }

    override fun showAppUsageList(list: List<AppUsage>) {
        appUsageList.value = list.filter {
            it.enableUninstall && it.packageName != packageName
        }.sortedByDescending {
            if (it.lastUsedTime > 0) it.lastUsedTime else it.installedTime
        }
    }

    override fun requirePermissionForAppUsage() {
        requestingPermission.value = true
    }
}
