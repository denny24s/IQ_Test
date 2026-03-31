package com.example.iqtest

import android.app.Application
import com.example.iqtest.core.di.AppContainer
import com.example.iqtest.core.di.DefaultAppContainer

class IQTestApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer(this)
    }
}
