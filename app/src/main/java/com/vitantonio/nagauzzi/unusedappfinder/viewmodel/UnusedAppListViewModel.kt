package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.PackageNameRepository
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Error
import com.vitantonio.nagauzzi.unusedappfinder.state.AppUsageState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UnusedAppListViewModel @Inject constructor(
    packageNameRepository: PackageNameRepository
) : ViewModel() {

    private val mutableShowingList = MutableSharedFlow<List<AppUsage>>()
    val showingList: SharedFlow<List<AppUsage>> = mutableShowingList

    private val mutableRequestingPermission = MutableSharedFlow<Boolean>()
    val requestingPermission: SharedFlow<Boolean> = mutableRequestingPermission

    init {
        AppUsageState.now.onEach { new ->
            when (new) {
                is Success -> {
                    mutableRequestingPermission.emit(false)
                    mutableShowingList.emit(new.list.filter {
                        it.enableUninstall && it.packageName != packageNameRepository.get()
                        true
                    }.sortedByDescending {
                        if (it.lastUsedTime > 0) it.lastUsedTime else it.installedTime
                    })
                }
                is Error -> {
                    if (new.exception is SecurityException) {
                        mutableRequestingPermission.emit(true)
                    }
                }
                else -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}
