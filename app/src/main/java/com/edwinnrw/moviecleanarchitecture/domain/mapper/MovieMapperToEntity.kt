package com.edwinnrw.moviecleanarchitecture.domain.mapper

import com.edwinnrw.moviecleanarchitecture.data.entities.MoviesDataSourceApi
import com.edwinnrw.moviecleanarchitecture.domain.entities.MoviesEntity


fun movieMapListToEntity(movies:List<MoviesDataSourceApi>) : List<MoviesEntity> =
        movies.map { mapItemToEntity(it) }

private fun mapItemToEntity(item:MoviesDataSourceApi): MoviesEntity =
        MoviesEntity(
                id = item.id?:0,
                adult = item.adult?:false,
                backdropPath = item.backdropPath?:"",
                genreIds = item.genreIds?: mutableListOf(),
                originalLanguage = item.originalLanguage?:"",
                originalTitle = item.originalTitle?:"",
                overview = item.overview?:"",
                popularity = item.popularity?:0.0,
                posterPath = item.posterPath?:"",
                releaseDate = item.releaseDate?:"",
                title = item.title?:"",
                video = item.video?:false,
                voteAverage = item.voteAverage?:0.0,
                voteCount = item.voteCount?:0
        )