package com.edwinnrw.moviecleanarchitecture.data.entities


import com.google.gson.annotations.SerializedName

data class BaseGenreDataSourceApi(
    @SerializedName("genres")
    val genreDataSourceApis: List<GenreDataSourceApi>?
)