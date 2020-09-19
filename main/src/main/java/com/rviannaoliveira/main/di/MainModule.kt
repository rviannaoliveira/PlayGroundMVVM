package com.rviannaoliveira.main.di

import androidx.lifecycle.ViewModel
import com.rviannaoliveira.di.ViewModelKey
import com.rviannaoliveira.main.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainCharactersViewModel(viewModel: MainViewModel): ViewModel

}
