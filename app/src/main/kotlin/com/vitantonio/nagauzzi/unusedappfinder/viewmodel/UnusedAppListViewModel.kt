package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetFilteredAndSortedAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.view.model.AppUsageUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UnusedAppListViewModel
    @Inject
    constructor(
        @param:ApplicationContext private val context: Context,
        private val getFilteredAndSortedAppUsages: GetFilteredAndSortedAppUsages,
    ) : ViewModel() {
        private val mutableShowingList = MutableStateFlow<List<AppUsageUiModel>>(emptyList())
        val showingList: StateFlow<List<AppUsageUiModel>> = mutableShowingList

        private val mutableRequestingPermission = MutableStateFlow(false)
        val requestingPermission: StateFlow<Boolean> = mutableRequestingPermission

        suspend fun reload() {
            getFilteredAndSortedAppUsages().onSuccess { appUsageList ->
                mutableRequestingPermission.emit(false)
                mutableShowingList.emit(appUsageList.map { it.toUiModel(context) })
            }.onFailure { exception ->
                if (exception is SecurityException) {
                    mutableRequestingPermission.emit(true)
                }
            }
        }
    }

private fun AppUsage.toUiModel(context: Context): AppUsageUiModel {
    val packageManager = context.packageManager
    val icon = try {
        packageManager.getApplicationIcon(packageName)
    } catch (e: Exception) {
        null
    }
    return AppUsageUiModel(
        name = name,
        packageName = packageName,
        activityName = activityName,
        icon = icon,
        installedTime = installedTime,
        lastUsedTime = lastUsedTime,
        enableUninstall = enableUninstall
    )
}
