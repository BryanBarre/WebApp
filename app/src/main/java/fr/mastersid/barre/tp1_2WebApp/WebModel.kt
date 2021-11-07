package fr.mastersid.barre.tp1_2WebApp

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 *Created by Bryan BARRE on 29/09/2021.
 */
class WebModel (
    state : SavedStateHandle
) : ViewModel() {
    private val _loadOutside: MutableLiveData<Uri> = MutableLiveData(Uri.EMPTY)
    val loadOutside: LiveData<Uri>
        get() = _loadOutside

    private val _pageTitle: MutableLiveData <String > = MutableLiveData("http://mastersid.univ-rouen.fr")
    val pageTitle: LiveData <String >
        get() = _pageTitle

    private val _urlPage:MutableLiveData<String> = MutableLiveData("http://mastersid.univ-rouen.fr")
    val urlPage:LiveData<String>
    get()= _urlPage

    fun updateLoadOutside(uri: Uri){
        _loadOutside.value=uri
    }

    fun updatepageTitle(title: String?){
        _pageTitle.value=title
    }

    fun updateUrl(url: String?){
        _urlPage.value=url
    }

}