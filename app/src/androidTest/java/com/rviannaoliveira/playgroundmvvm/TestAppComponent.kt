package com.rviannaoliveira.playgroundmvvm

import android.app.Application
import com.rviannaoliveira.base.di.BaseModule
import com.rviannaoliveira.cache.CacheModule
import com.rviannaoliveira.di.ViewModelFactoryModule
import com.rviannaoliveira.networking.di.NetworkModule
import com.rviannaoliveira.playgroundmvvm.di.ActivityBuilderModule
import com.rviannaoliveira.playgroundmvvm.di.AppComponent
import com.rviannaoliveira.playgroundmvvm.di.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    ViewModelFactoryModule::class,
    AppModule::class,
    MockNetworkModule::class,
    BaseModule::class,
    CacheModule::class
])
interface TestAppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: CustomApplication)

    override fun inject(instance: DaggerApplication)

    fun getMockWebServer(): MockWebServer

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }
}