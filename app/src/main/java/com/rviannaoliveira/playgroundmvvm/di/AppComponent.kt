package com.rviannaoliveira.playgroundmvvm.di

import android.app.Application
import com.rviannaoliveira.base.di.BaseModule
import com.rviannaoliveira.cache.CacheModule
import com.rviannaoliveira.di.ViewModelFactoryModule
import com.rviannaoliveira.networking.di.NetworkModule
import com.rviannaoliveira.playgroundmvvm.CustomApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class,
        AppModule::class,
        NetworkModule::class,
        BaseModule::class,
        CacheModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: CustomApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
