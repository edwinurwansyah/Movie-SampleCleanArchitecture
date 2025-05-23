package com.edwinnrw.moviecleanarchitecture.data.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseMovieDataSourceApi(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val moviesDataSourceApis: List<MoviesDataSourceApi>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
)