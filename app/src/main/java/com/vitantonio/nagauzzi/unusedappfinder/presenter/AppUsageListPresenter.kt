package com.vitantonio.nagauzzi.unusedappfinder.presenter

import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage

interface AppUsageListPresenter {
    fun showAppUsageList(list: List<AppUsage>)
    fun requirePermissionForAppUsage()
}