package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetFilteredAndSortedAppUsages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UnusedAppListViewModel
    @Inject
    constructor(
        private val getFilteredAndSortedAppUsages: GetFilteredAndSortedAppUsages,
    ) : ViewModel() {
        private val mutableShowingList = MutableStateFlow<List<AppUsage>>(emptyList())
        val showingList: StateFlow<List<AppUsage>> = mutableShowingList

        private val mutableRequestingPermission = MutableStateFlow(false)
        val requestingPermission: StateFlow<Boolean> = mutableRequestingPermission

        suspend fun reload() {
            getFilteredAndSortedAppUsages().onSuccess { appUsageList ->
                mutableRequestingPermission.emit(false)
                mutableShowingList.emit(appUsageList)
            }.onFailure { exception ->
                if (exception is SecurityException) {
                    mutableRequestingPermission.emit(true)
                }
            }
        }
    }
