package com.example.fertvlive

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fertvlive.databinding.TemplateChangeUrlBinding
import com.example.fertvlive.prefs.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {

    var URI_PATH = Prefs.url

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onResume() {
        super.onResume()
        load()

        mainVideo.setOnErrorListener { _, _, _ ->
            alert {
                title = getString(R.string.error_loading)
                positiveButton(getString(R.string.retry_text)) {
                    load()
                }
                negativeButton(getString(R.string.close_text)){
                    finish()
                }
            }.show()
            true
        }

        mainVideo.setOnPreparedListener {
            pbLoading.visibility = View.GONE
            Prefs.url = URI_PATH
        }

        changeUrl.setOnClickListener {
            changeUrl()
        }

    }

    private fun load(){
        pbLoading.visibility = View.VISIBLE
        mainVideo.setVideoURI(Uri.parse(URI_PATH))
        mainVideo.start()
    }

    private fun changeUrl() =
        alert {
            title = getString(R.string.change_url_text)
            val binding: TemplateChangeUrlBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.template_change_url, null, false)
            customView = binding.root
            yesButton {
                URI_PATH = binding.urlText.toString()
                mainVideo.stopPlayback()
                mainVideo.setVideoURI(Uri.parse(URI_PATH))
                mainVideo.start()
            }
            cancelButton { }
        }.show()

}
