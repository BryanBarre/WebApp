package fr.mastersid.barre.tp1_2WebApp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import fr.mastersid.barre.tp1_2WebApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true
        //val questionListModel : QuestionListModel by viewModels ()

        val webChromeClient = MyWebChromeClient ()

        val webModel:WebModel by viewModels()
        val webViewClient = MyWebViewClient (webModel)

        binding.webView.webViewClient = webViewClient
        webModel.loadOutside.observe(this) { url ->
            if (url != Uri.EMPTY) {
                val intent = Intent(Intent.ACTION_VIEW , url)
                startActivity(intent)
            }
        }
        binding.webView.webChromeClient = webChromeClient
        webChromeClient.progress.observe(this) { progress ->
            binding.determinateBar.progress = progress
        }

        webModel.pageTitle.observe(this) { value ->
            title = value
        }

        binding.buttonForward.hide()
        binding.buttonForward.setOnClickListener {
            onForwardPressed()
        }


        webModel.urlPage.observe(this){state->
            binding.webView.loadUrl(state)

        }
    }

    override fun onBackPressed () {
        //Toast.makeText(this,"hehe",Toast.LENGTH_SHORT).show()
        if (binding.webView.canGoBack ()) {
            binding.webView.goBack ()
            binding.buttonForward.show()
        } else {
            super.onBackPressed ()
        }

    }

    private fun onForwardPressed(){
        if (binding.webView.canGoForward()){
            binding.webView.goForward()
            if (binding.webView.canGoForward()){
                binding.buttonForward.show()
            }
            else{
                binding.buttonForward.hide()
            }
        }
        else{
            Toast.makeText(this,"you're not supposed to go any further",Toast.LENGTH_SHORT).show()

        }
    }


}
