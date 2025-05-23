package com.edwinnrw.moviecleanarchitecture.data.entities


import com.alibaba.fastjson.annotation.JSONField

data class MoviesDataSourceApi(
    @JSONField(name = "adult")
    val adult: Boolean?,
    @JSONField(name = "backdrop_path")
    val backdropPath: String?,
    @JSONField(name = "genre_ids")
    val genreIds: List<Int>?,
    @JSONField(name = "id")
    val id: Int?,
    @JSONField(name = "original_language")
    val originalLanguage: String?,
    @JSONField(name = "original_title")
    val originalTitle: String?,
    @JSONField(name = "overview")
    val overview: String?,
    @JSONField(name = "popularity")
    val popularity: Double?,
    @JSONField(name = "poster_path")
    val posterPath: String?,
    @JSONField(name = "release_date")
    val releaseDate: String?,
    @JSONField(name = "title")
    val title: String?,
    @JSONField(name = "video")
    val video: Boolean?,
    @JSONField(name = "vote_average")
    val voteAverage: Double?,
    @JSONField(name = "vote_count")
    val voteCount: Int?
)