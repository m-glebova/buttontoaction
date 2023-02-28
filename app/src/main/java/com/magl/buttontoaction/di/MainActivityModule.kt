package com.magl.buttontoaction.di

import androidx.lifecycle.ViewModel
import com.magl.buttontoaction.presentation.MainActivity
import com.magl.buttontoaction.presentation.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindViewModel(viewModel: MainActivityViewModel): ViewModel
}
