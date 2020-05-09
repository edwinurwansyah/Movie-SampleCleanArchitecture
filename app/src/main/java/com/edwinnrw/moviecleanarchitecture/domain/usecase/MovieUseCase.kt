package com.edwinnrw.moviecleanarchitecture.domain.usecase

import com.edwinnrw.moviecleanarchitecture.data.common.MovieRepositoryContract
import com.edwinnrw.moviecleanarchitecture.domain.common.MovieUseCaseContract
import com.edwinnrw.moviecleanarchitecture.domain.common.ResultState
import com.edwinnrw.moviecleanarchitecture.domain.mapper.movieMapListToEntity
import com.edwinnrw.moviecleanarchitecture.presentation.common.observableIo
import io.reactivex.Observable

class MovieUseCase(private val movieRepositoryContract: MovieRepositoryContract) : MovieUseCaseContract {
    override fun getMovieNowPlaying(): Observable<ResultState> {
        return  movieRepositoryContract.getMovieNowPlaying()
            .map {
                ResultState.Success(movieMapListToEntity(it.moviesDataSourceApis?: mutableListOf()),"")
            }.compose(observableIo())
    }

    override fun getMovieUpcoming(): Observable<ResultState> {
        return  movieRepositoryContract.getMovieUpcoming()
            .map {
                ResultState.Success(movieMapListToEntity(it.moviesDataSourceApis?: mutableListOf()),"")
            }.compose(observableIo())
    }

    override fun getMovieTrending(): Observable<ResultState> {
        return  movieRepositoryContract.getMoviePopular()
            .map {
                ResultState.Success(movieMapListToEntity(it.moviesDataSourceApis?: mutableListOf()),"")
            }.compose(observableIo())
    }

}