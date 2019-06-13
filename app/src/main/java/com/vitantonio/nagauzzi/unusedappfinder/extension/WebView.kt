package com.vitantonio.nagauzzi.unusedappfinder.extension

import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("url")
fun WebView.bindUrl(url: String?) {
    if (url != null) {
        loadUrl(url)
    }
}