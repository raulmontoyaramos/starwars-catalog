package com.example.practicar_android.di

import com.example.practicar_android.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

  /*  @Component.Builder
    interface Builder {
        fun appModule(application: Application)
        fun build(): AppComponent
    }*/

    fun inject(mainActivity: MainActivity)
}

