package com.example.cordovacrash

import org.apache.cordova.CordovaPreferences
import org.apache.cordova.CordovaWebViewEngine
import org.apache.cordova.engine.SystemWebView
import org.apache.cordova.engine.SystemWebViewEngine

class MySystemWebViewEngine(
    webview: SystemWebView,
    preferences: CordovaPreferences
) : SystemWebViewEngine(webview, preferences) {

    val client: CordovaWebViewEngine.Client? = super.client

}