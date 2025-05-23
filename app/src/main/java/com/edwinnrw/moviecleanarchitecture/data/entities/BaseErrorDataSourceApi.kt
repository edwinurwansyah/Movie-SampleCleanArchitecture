package com.edwinnrw.moviecleanarchitecture.data.entities


import com.alibaba.fastjson.annotation.JSONField

data class BaseErrorDataSourceApi(
    @JSONField(name = "status_code")
    val statusCode: Int?,
    @JSONField(name = "status_message")
    val statusMessage: String?,
    @JSONField(name = "success")
    val success: Boolean?
)