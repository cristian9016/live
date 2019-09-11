package com.example.fertvlive.prefs

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object Prefs {

    lateinit var prefs: SharedPreferences
    const val URL_PREFS = "URL_PREFS"

    fun init(context: Context) {
        prefs = context.getSharedPreferences("video_prefs", Activity.MODE_PRIVATE)
    }

    var url: String?
        get() = prefs.getString(
            URL_PREFS,
            "https://go5lm4kolawb-hls-live.5centscdn.com/fertv/ab6c040066603ef2519d512b21dce9ab.sdp/index.m3u8"
        )
        set(value) = prefs.edit().putString(URL_PREFS, value).apply()


}