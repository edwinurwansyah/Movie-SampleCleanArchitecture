package com.edwinnrw.moviecleanarchitecture.data.entities


import com.google.gson.annotations.SerializedName

data class BaseErrorDataSourceApi(
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?
)