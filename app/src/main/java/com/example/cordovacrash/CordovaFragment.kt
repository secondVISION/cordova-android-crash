package com.example.cordovacrash

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cordova.*
import org.apache.cordova.*
import org.apache.cordova.engine.SystemWebChromeClient
import java.util.concurrent.Executors

open class CordovaFragment : Fragment() {

    protected lateinit var cordovaWebViewImpl: CordovaWebViewImpl
    private lateinit var pluginEntries: List<PluginEntry>
    private lateinit var cordovaPreferences: CordovaPreferences

    private lateinit var cordovaInterface: CordovaInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_cordova, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCordovaWebview()
        loadUrl(requireArguments().getString(KEY_URL, "https://google.com"))
    }

    override fun onDestroyView() {
        webview.removeAllViews()
        webview.destroy()
        super.onDestroyView()
    }

    private fun addPreferences(prefs: CordovaPreferences) {
        prefs.setPreferencesBundle(requireActivity().intent.extras)
    }

    fun initCordovaWebview() {
        val parser = ConfigXmlParser()
        parser.parse(context)

        pluginEntries = parser.pluginEntries
        cordovaPreferences = parser.preferences
        addPreferences(cordovaPreferences)

        val systemWebViewEngine = MySystemWebViewEngine(webview, cordovaPreferences)
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = MySystemWebViewClient(systemWebViewEngine)
        webview.webChromeClient = SystemWebChromeClient(systemWebViewEngine)

        cordovaInterface = CordovaInterface()
        cordovaWebViewImpl = CordovaWebViewImpl(systemWebViewEngine)
        cordovaWebViewImpl.clearHistory()
        cordovaWebViewImpl.init(cordovaInterface, pluginEntries, cordovaPreferences)
        cordovaWebViewImpl.pluginManager.init()
    }

    fun loadUrl(url: String) {
        cordovaWebViewImpl.loadUrl(url)
    }

    inner class CordovaInterface : SimpleCordovaInterface() {

        override fun getHandler() = this@CordovaFragment

        override fun getActivity() = this@CordovaFragment.activity

        override fun getContext(): Context = this@CordovaFragment.requireContext()

        override fun getThreadPool() = Executors.newCachedThreadPool()

        override fun startActivityForResult(plugin: CordovaPlugin?, intent: Intent?, requestCode: Int) {
            startActivityForResult(intent, requestCode)
        }

        override fun hasPermission(permission: String?) = false

        override fun requestPermission(plugin: CordovaPlugin?, requestcode: Int, permission: String?) = Unit

        override fun requestPermissions(plugin: CordovaPlugin?, requestcode: Int, permissions: Array<out String>?)  = Unit

        override fun setActivityResultCallback(plugin: CordovaPlugin?)  = Unit
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = Unit

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)  = Unit

    companion object {
        private val KEY_URL = "url"

        fun newInstance(url: String) = CordovaFragment()
            .apply {
                arguments = Bundle().apply {
                    putString(KEY_URL, url)
                }
            }
    }

}
