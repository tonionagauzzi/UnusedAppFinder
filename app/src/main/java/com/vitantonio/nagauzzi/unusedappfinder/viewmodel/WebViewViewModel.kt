package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(): ViewModel() {
    val url = MutableLiveData<String>()
}
