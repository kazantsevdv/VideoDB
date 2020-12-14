package com.example.videodb

import android.app.Application
import com.example.videodb.di.AppComponent
import com.example.videodb.di.DaggerAppComponent
import com.example.videodb.di.modules.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
        val component get() = instance._appComponent
    }

    private lateinit var _appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        _appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}