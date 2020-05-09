package com.edwinnrw.moviecleanarchitecture.data.entities


import com.google.gson.annotations.SerializedName

data class BaseMovieDataSourceApi(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val moviesDataSourceApis: List<MoviesDataSourceApi>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)