package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetFilteredAndSortedAppUsages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnusedAppListViewModel
    @Inject
    constructor(
        private val getFilteredAndSortedAppUsages: GetFilteredAndSortedAppUsages,
    ) : ViewModel() {
        private val mutableAppUsageList = MutableStateFlow<List<AppUsage>>(emptyList())
        val appUsageList: StateFlow<List<AppUsage>> = mutableAppUsageList

        private val mutableRequestingPermission = MutableStateFlow(false)
        val requestingPermission: StateFlow<Boolean> = mutableRequestingPermission

        private val mutableIsReloading = MutableStateFlow(false)
        val isReloading: StateFlow<Boolean> = mutableIsReloading

        @Volatile
        private var reloadJob: Job? = null

        fun reload() {
            reloadJob?.cancel()
            reloadJob =
                viewModelScope.launch {
                    mutableIsReloading.emit(true)
                    getFilteredAndSortedAppUsages().onSuccess { appUsageList ->
                        mutableRequestingPermission.emit(false)
                        mutableAppUsageList.emit(appUsageList)
                    }.onFailure { exception ->
                        if (exception is SecurityException) {
                            mutableRequestingPermission.emit(true)
                        }
                    }
                    mutableIsReloading.emit(false)
                }
        }
    }
