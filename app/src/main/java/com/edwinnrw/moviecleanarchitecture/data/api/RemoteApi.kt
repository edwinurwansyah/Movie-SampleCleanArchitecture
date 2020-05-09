package com.edwinnrw.moviecleanarchitecture.data.api

import com.edwinnrw.moviecleanarchitecture.data.entities.BaseMovieDataSourceApi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteApi {
    @GET("./3/movie/now_playing")
    fun getNowPlaying(@Query("api_key") apiKey: String) : Observable<BaseMovieDataSourceApi>

    @GET("./3/movie/upcoming")
    fun getUpcoming(@Query("api_key") apiKey: String) : Observable<BaseMovieDataSourceApi>

    @GET("./3/movie/popular")
    fun getTrending(@Query("api_key") apiKey: String) : Observable<BaseMovieDataSourceApi>


}