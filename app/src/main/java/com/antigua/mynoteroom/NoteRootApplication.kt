package com.antigua.mynoteroom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteRootApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}