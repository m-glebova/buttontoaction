package com.magl.buttontoaction.di

import com.magl.buttontoaction.core.action.ActionRepository
import com.magl.buttontoaction.data.action.AppActionRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ApplicationModuleBinds::class])
class ApplicationModule {

}

@Module
abstract class ApplicationModuleBinds {

    @Binds
    abstract fun bindActionRepository(repo: AppActionRepository): ActionRepository
}
