package com.vitantonio.nagauzzi.unusedappfinder.usecase

import com.vitantonio.nagauzzi.unusedappfinder.presenter.AppUsageListPresenter
import com.vitantonio.nagauzzi.unusedappfinder.repository.AppUsageRepository

class GetAppUsages(
    private val repository: AppUsageRepository
) {
    lateinit var presenter: AppUsageListPresenter

    fun execute() {
        try {
            val appUsageList = repository.get()
            presenter.showAppUsageList(appUsageList)
        } catch (e: SecurityException) {
            presenter.requirePermissionForAppUsage()
        }
    }
}