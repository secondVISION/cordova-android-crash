package com.example.cordovacrash

import android.graphics.Bitmap
import android.webkit.WebView
import org.apache.cordova.engine.SystemWebViewClient

class MySystemWebViewClient(
    private val engine: MySystemWebViewEngine
) : SystemWebViewClient(engine) {

    override fun onPageStarted(view: WebView?, url: String, favicon: Bitmap?) {
        if (!view?.url.isNullOrEmpty() && view?.url == url) {
            super.onPageStarted(view, url, favicon)
        } else {
            engine.client?.onPageStarted(url)
        }
    }

}