package com.edwinnrw.moviecleanarchitecture.presentation.di

import com.edwinnrw.moviecleanarchitecture.data.api.RemoteApi
import com.edwinnrw.moviecleanarchitecture.data.common.MovieRepositoryContract
import com.edwinnrw.moviecleanarchitecture.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {


    @Singleton
    @Provides
    internal fun provideAuthRepository(remoteApi: RemoteApi): MovieRepositoryContract {
        return MovieRepository(remoteApi)

    }



}