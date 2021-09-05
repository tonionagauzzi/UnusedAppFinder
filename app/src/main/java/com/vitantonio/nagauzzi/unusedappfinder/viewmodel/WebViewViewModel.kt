package com.vitantonio.nagauzzi.unusedappfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewViewModel: ViewModel() {
    val url = MutableLiveData<String>()
}
