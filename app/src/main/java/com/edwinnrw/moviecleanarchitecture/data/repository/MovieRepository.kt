package com.edwinnrw.moviecleanarchitecture.data.repository

import com.edwinnrw.moviecleanarchitecture.BuildConfig
import com.edwinnrw.moviecleanarchitecture.data.api.RemoteApi
import com.edwinnrw.moviecleanarchitecture.data.common.MovieRepositoryContract


class MovieRepository(private  val api:RemoteApi) : MovieRepositoryContract {
    override fun getMovieNowPlaying() =  api.getNowPlaying(BuildConfig.API_KEY)

    override fun getMovieUpcoming() =  api.getUpcoming(BuildConfig.API_KEY)

    override fun getMoviePopular() =  api.getTrending(BuildConfig.API_KEY)


}