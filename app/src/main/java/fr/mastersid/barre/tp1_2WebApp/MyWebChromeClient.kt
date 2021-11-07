package fr.mastersid.barre.tp1_2WebApp

import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 *Created by Bryan BARRE on 28/09/2021.
 */
class MyWebChromeClient : WebChromeClient() {
    private val _progress = MutableLiveData (0)
    val progress: LiveData<Int>
        get() = _progress
    override fun onProgressChanged(view: WebView, newProgress: Int) {
        super.onProgressChanged(view , newProgress)
        _progress.value = newProgress

    }

}