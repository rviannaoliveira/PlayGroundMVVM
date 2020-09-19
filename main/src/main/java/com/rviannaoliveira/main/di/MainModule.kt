package com.rviannaoliveira.main.di

import androidx.lifecycle.ViewModel
import com.rviannaoliveira.di.ViewModelKey
import com.rviannaoliveira.main.data.MainRepository
import com.rviannaoliveira.main.data.MainRepositoryImpl
import com.rviannaoliveira.main.data.MainRepositoryImpl_Factory
import com.rviannaoliveira.main.presentation.MainViewModel
import com.rviannaoliveira.main.data.service.MainService
import com.rviannaoliveira.main.domain.MainInteractor
import com.rviannaoliveira.main.domain.MainInteractorImpl
import com.rviannaoliveira.main.domain.MainInteractorImpl_Factory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindMainInteractor(interactor: MainInteractorImpl): MainInteractor

    @Binds
    abstract fun bindMainRepository(repository: MainRepositoryImpl): MainRepository

    @Module
    companion object{
        @JvmStatic
        @Provides
        fun provideService(retrofit: Retrofit): MainService =
            retrofit.create(MainService::class.java)
    }

}
