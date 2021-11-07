package fr.mastersid.barre.tp1_2WebApp

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 *Created by Bryan BARRE on 28/09/2021.
 */
class MyWebViewClient(private val webModel: WebModel): WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest):
            Boolean {
        if (request.url.host == "mastersid.univ-rouen.fr" || request.url.host == "www.insa-rouen.fr") {
            webModel.updateUrl(url= request.url.toString())

            return false // ouverture dans la WebView
        }
        webModel.updateLoadOutside(uri = request.url)

        return true

    }

    override fun onPageFinished(view: WebView , url: String) {
        super.onPageFinished(view , url)
        webModel.updatepageTitle(title = view.title)
        //_pageTitle.value = view.title
    }



}
