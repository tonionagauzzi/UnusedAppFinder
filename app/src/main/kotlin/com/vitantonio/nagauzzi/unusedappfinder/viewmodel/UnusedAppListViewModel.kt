package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import com.vitantonio.nagauzzi.unusedappfinder.repository.PackageNameRepository
import com.vitantonio.nagauzzi.unusedappfinder.result.AppUsageResult
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UnusedAppListViewModel @Inject constructor(
    private val getAppUsages: GetAppUsages,
    private val packageNameRepository: PackageNameRepository,
) : ViewModel() {

    private val mutableShowingList = MutableStateFlow<List<AppUsage>>(emptyList())
    val showingList: StateFlow<List<AppUsage>> = mutableShowingList

    private val mutableRequestingPermission = MutableStateFlow<Boolean>(false)
    val requestingPermission: StateFlow<Boolean> = mutableRequestingPermission

    suspend fun reload() {
        when (val result = getAppUsages()) {
            is AppUsageResult.Success -> {
                mutableRequestingPermission.emit(false)
                mutableShowingList.emit(
                    result.list.filter {
                        it.enableUninstall && it.packageName != packageNameRepository.get()
                        true
                    }.sortedByDescending {
                        if (it.lastUsedTime > 0) it.lastUsedTime else it.installedTime
                    }
                )
            }

            is AppUsageResult.Error -> {
                if (result.exception is SecurityException) {
                    mutableRequestingPermission.emit(true)
                }
            }
        }
    }
}
