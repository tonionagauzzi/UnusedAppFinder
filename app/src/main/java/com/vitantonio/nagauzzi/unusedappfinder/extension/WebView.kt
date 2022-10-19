package com.vitantonio.nagauzzi.unusedappfinder.extension

import android.webkit.WebView

fun WebView.bindUrl(url: String?) {
    if (url != null) {
        loadUrl(url)
    }
}