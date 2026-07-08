package com.example.hi_quran

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import android.util.Log

@HiltAndroidApp
class HiQuranApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("HiQuranApp", "Application started")
    }
}
