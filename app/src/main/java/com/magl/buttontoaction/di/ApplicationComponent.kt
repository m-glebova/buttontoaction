package com.magl.buttontoaction.di

import android.content.Context
import com.magl.buttontoaction.ButtonToActionApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        CoroutinesModule::class,
        NetworkModule::class,
        PersistenceModule::class,
        MainActivityModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ButtonToActionApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
