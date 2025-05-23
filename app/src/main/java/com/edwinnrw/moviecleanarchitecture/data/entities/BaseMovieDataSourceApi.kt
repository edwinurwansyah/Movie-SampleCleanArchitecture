package com.edwinnrw.moviecleanarchitecture.data.entities


import com.alibaba.fastjson.annotation.JSONField

data class BaseMovieDataSourceApi(
    @JSONField(name = "page")
    val page: Int?,
    @JSONField(name = "results")
    val moviesDataSourceApis: List<MoviesDataSourceApi>?,
    @JSONField(name = "total_pages")
    val totalPages: Int?,
    @JSONField(name = "total_results")
    val totalResults: Int?
)

