package com.example.practicar_android.domain.model

import android.app.Application

class StarWarsApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}