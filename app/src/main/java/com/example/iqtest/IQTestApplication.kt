package com.example.iqtest

import android.app.Application
import com.example.iqtest.core.di.AppContainer
import com.example.iqtest.core.di.DefaultAppContainer
import com.google.firebase.FirebaseApp

class IQTestApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        // Safe no-op when Firebase isn't configured (returns null).
        FirebaseApp.initializeApp(this)
        appContainer = DefaultAppContainer(this)
    }
}
