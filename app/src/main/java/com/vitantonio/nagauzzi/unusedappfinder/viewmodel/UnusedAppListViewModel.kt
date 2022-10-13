package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.PackageNameRepository
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Error
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UnusedAppListViewModel @Inject constructor(
    packageNameRepository: PackageNameRepository
) : ViewModel() {

    private val mutableShowingList = MutableStateFlow(emptyList<AppUsage>())
    val showingList: StateFlow<List<AppUsage>> = mutableShowingList
    val requestingPermission = MutableLiveData(false)

    init {
        AppUsageState.now.onEach { new ->
            when (new) {
                is Success -> {
                    requestingPermission.value = false
                    mutableShowingList.emit(new.list.filter {
                        it.enableUninstall && it.packageName != packageNameRepository.get()
                    }.sortedByDescending {
                        if (it.lastUsedTime > 0) it.lastUsedTime else it.installedTime
                    })
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
}
