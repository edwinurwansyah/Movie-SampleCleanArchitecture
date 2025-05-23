package com.edwinnrw.moviecleanarchitecture.data.entities


import com.alibaba.fastjson.annotation.JSONField

data class GenreDataSourceApi(
    @JSONField(name = "id")
    val id: Int?,
    @JSONField(name = "name")
    val name: String?
)