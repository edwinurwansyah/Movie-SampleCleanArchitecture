package com.edwinnrw.moviecleanarchitecture.presentation.di


import androidx.lifecycle.ViewModelProvider
import com.edwinnrw.moviecleanarchitecture.presentation.viewModel.BaseViewModel
import com.edwinnrw.moviecleanarchitecture.presentation.viewModel.MovieViewModel
import com.edwinnrw.moviecleanarchitecture.presentation.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel) : BaseViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory



}