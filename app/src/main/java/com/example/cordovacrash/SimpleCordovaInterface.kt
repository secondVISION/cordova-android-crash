package com.example.cordovacrash

import android.content.Intent
import org.apache.cordova.CordovaPlugin

abstract class SimpleCordovaInterface: ExtendedCordovaInterface {

    override fun setActivityResultCallback(plugin: CordovaPlugin?) {}

    override fun requestPermissions(plugin: CordovaPlugin?, p1: Int, p2: Array<out String>?) {}

    override fun startActivityForResult(plugin: CordovaPlugin?, p1: Intent?, p2: Int) {}

    override fun onMessage(message: String?, p1: Any?) = this

    override fun hasPermission(p0: String?) = true

    override fun requestPermission(p0: CordovaPlugin?, p1: Int, p2: String?) {}

}