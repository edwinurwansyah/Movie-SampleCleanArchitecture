package com.edwinnrw.moviecleanarchitecture.presentation.di


import com.edwinnrw.moviecleanarchitecture.data.common.MovieRepositoryContract
import com.edwinnrw.moviecleanarchitecture.domain.common.MovieUseCaseContract
import com.edwinnrw.moviecleanarchitecture.domain.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UseCaseModule {


    @Provides
    @Singleton
    internal fun provideMovieUseCase(repositoryContract: MovieRepositoryContract): MovieUseCaseContract {
        return MovieUseCase(repositoryContract)

    }

}