package com.example.brewbuddy.domain.di

import android.app.Application
import com.example.brewbuddy.data.local.prefs.Prefs
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CoffeeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        com.example.brewbuddy.data.local.prefs.Prefs.init(this)
    }
}
