package com.edwinnrw.moviecleanarchitecture.data.entities


import com.google.gson.annotations.SerializedName

data class GenreDataSourceApi(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)