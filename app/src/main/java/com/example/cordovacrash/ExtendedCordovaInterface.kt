package com.example.cordovacrash

import org.apache.cordova.CordovaInterface

interface ExtendedCordovaInterface: CordovaInterface {

    fun getHandler(): Any

}
