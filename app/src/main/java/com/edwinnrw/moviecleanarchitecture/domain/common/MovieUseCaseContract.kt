package com.edwinnrw.moviecleanarchitecture.domain.common

import io.reactivex.Observable

interface MovieUseCaseContract {
    fun getMovieNowPlaying() : Observable<ResultState>

    fun getMovieUpcoming() : Observable<ResultState>

    fun getMovieTrending() : Observable<ResultState>
}