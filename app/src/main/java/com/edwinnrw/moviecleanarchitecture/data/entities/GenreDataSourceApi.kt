package com.edwinnrw.moviecleanarchitecture.data.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDataSourceApi(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?
)