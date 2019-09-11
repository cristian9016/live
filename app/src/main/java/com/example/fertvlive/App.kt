package com.example.fertvlive

import android.app.Application
import com.example.fertvlive.prefs.Prefs

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefs.init(this)
    }

}