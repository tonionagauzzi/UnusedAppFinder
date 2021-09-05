package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Error
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Success
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class UnusedAppListViewModel(
    private val context: Context,
    private val useCase: GetAppUsages
) : ViewModel() {

    val appUsageList = MutableLiveData<List<AppUsage>>()
    val requestingPermission = MutableLiveData<Boolean>()

    init {
        AppUsageState.now.onEach { new ->
            when (new) {
                is Success -> {
                    appUsageList.value = new.list.filter {
                        it.enableUninstall && it.packageName != context.packageName
                    }.sortedByDescending {
                        if (it.lastUsedTime > 0) it.lastUsedTime else it.installedTime
                    }
                }
                is Error -> {
                    if (new.exception is SecurityException) {
                        requestingPermission.value = true
                    }
                }
                else -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAppUsages() {
        requestingPermission.value = false
        viewModelScope.launch {
            useCase.execute()
        }
    }
}
