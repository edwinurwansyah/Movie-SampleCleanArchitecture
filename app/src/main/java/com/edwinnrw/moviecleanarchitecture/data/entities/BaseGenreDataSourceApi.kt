package com.edwinnrw.moviecleanarchitecture.data.entities


import com.alibaba.fastjson.annotation.JSONField

data class BaseGenreDataSourceApi(
    @JSONField(name = "genres")
    val genreDataSourceApis: List<GenreDataSourceApi>?
)