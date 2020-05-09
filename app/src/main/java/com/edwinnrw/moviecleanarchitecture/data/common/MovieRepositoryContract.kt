package com.edwinnrw.moviecleanarchitecture.data.common

import com.edwinnrw.moviecleanarchitecture.data.entities.BaseMovieDataSourceApi
import io.reactivex.Observable

interface MovieRepositoryContract {

    fun getMovieNowPlaying() : Observable<BaseMovieDataSourceApi>

    fun getMovieUpcoming() : Observable<BaseMovieDataSourceApi>

    fun getMoviePopular() : Observable<BaseMovieDataSourceApi>


}