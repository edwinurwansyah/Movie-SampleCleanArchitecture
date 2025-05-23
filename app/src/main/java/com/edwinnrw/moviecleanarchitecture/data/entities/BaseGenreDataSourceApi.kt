package com.edwinnrw.moviecleanarchitecture.data.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseGenreDataSourceApi(
    @SerialName("genres")
    val genreDataSourceApis: List<GenreDataSourceApi>?
)