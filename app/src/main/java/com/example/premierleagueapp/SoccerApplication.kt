package com.example.premierleagueapp

import android.app.Application
import com.example.premierleagueapp.data.AppContainer
import com.example.premierleagueapp.data.DefaultAppContainer

class SoccerApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
