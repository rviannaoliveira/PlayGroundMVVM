package com.rviannaoliveira.detail.di

import androidx.lifecycle.ViewModel
import com.rviannaoliveira.detail.data.DetailRepository
import com.rviannaoliveira.detail.data.DetailRepositoryImpl
import com.rviannaoliveira.detail.domain.DetailInteractor
import com.rviannaoliveira.detail.domain.DetailInteractorImpl
import com.rviannaoliveira.detail.presentation.DetailActivity
import com.rviannaoliveira.detail.presentation.DetailViewModel
import com.rviannaoliveira.detail.presentation.DetailUiModel
import com.rviannaoliveira.detail.presentation.DetailUiModelImpl
import com.rviannaoliveira.di.ViewModelKey
import com.rviannaoliveira.shared.extensions.requireString
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindRepository(repositoryImpl: DetailRepositoryImpl) : DetailRepository

    @Binds
    abstract fun bindInteractor(interactor : DetailInteractorImpl) : DetailInteractor

    @Binds
    abstract fun bindUiModel(uiModel : DetailUiModelImpl) : DetailUiModel

    @Module
    companion object{
        @JvmStatic
        @ID
        @Provides
        fun providesId(activity: DetailActivity): String =
            activity.requireString(DetailActivity.ID)
    }
}
