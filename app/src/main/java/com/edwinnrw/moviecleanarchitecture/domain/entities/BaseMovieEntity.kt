package com.edwinnrw.moviecleanarchitecture.domain.entities


data class BaseMovieEntity(
    val page: Int,
    val moviesDataSourceApis: List<MoviesEntity>,
    val totalPages: Int,
    val totalResults: Int
)