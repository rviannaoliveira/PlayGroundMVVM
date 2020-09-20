package com.rviannaoliveira.playgroundmvvm.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: Application): Context

    @Module
    companion object{
        @Provides
        @JvmStatic
        @Singleton
        fun provideAppResources(application: Application): Resources = application.resources
    }
}
