package com.rviannaoliveira.playgroundmvvm.di

import com.rviannaoliveira.di.ActivityScope
import com.rviannaoliveira.main.di.MainModule
import com.rviannaoliveira.main.presentation.MainActivity
import com.rviannaoliveira.playgroundmvvm.presentation.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [
        MainModule::class
    ])
    abstract fun bindMainActivity(): MainActivity
}
