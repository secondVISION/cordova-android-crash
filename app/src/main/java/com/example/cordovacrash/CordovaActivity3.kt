package com.example.cordovacrash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CordovaActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cordova3)
        val url = "https://github.com"
        loadCordova(url)
    }

    fun loadCordova(url: String) {
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, CordovaFragment.newInstance(url), "CordovaFragment")
        transaction.commit()
    }

}