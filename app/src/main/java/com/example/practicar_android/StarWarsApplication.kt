package com.example.practicar_android

import android.app.Application
import com.example.practicar_android.di.AppComponent
import com.example.practicar_android.di.AppModule
import com.example.practicar_android.di.DaggerAppComponent

class StarWarsApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
