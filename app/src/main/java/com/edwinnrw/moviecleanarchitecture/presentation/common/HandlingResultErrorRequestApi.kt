package com.edwinnrw.moviecleanarchitecture.presentation.common

import com.alibaba.fastjson.JSON
import com.edwinnrw.moviecleanarchitecture.data.entities.BaseErrorDataSourceApi
import com.edwinnrw.moviecleanarchitecture.domain.common.ResultState
import retrofit2.HttpException


fun getResultStateError(error: Throwable) : ResultState {
    return if (error is HttpException) {
        when (error.code()) {
            504 -> {
                ResultState.NoConnection(throwable = Throwable("No Connection"))
            }
            400 -> {
                var errorResponse: BaseErrorDataSourceApi? = null
                error.response()?.errorBody()?.let {
                    val jsonString = it.string() // important: read once
                    errorResponse = JSON.parseObject(jsonString, BaseErrorDataSourceApi::class.java)
                }
                return ResultState.BadRequest(throwable = Throwable(errorResponse?.statusMessage?:""))

            }
            401 -> {
                var errorResponse: BaseErrorDataSourceApi? = null
                error.response()?.errorBody()?.let {
                    val jsonString = it.string() // important: read once
                    errorResponse = JSON.parseObject(jsonString, BaseErrorDataSourceApi::class.java)
                }
                return ResultState.Unauthorized(throwable = Throwable(errorResponse?.statusMessage?:""))

            }
            404 -> {
                return ResultState.NotFound(throwable = Throwable("Not Found"))

            }
            403->{
                var errorResponse: BaseErrorDataSourceApi? = null
                error.response()?.errorBody()?.let {
                    val jsonString = it.string() // important: read once
                    errorResponse = JSON.parseObject(jsonString, BaseErrorDataSourceApi::class.java)
                }
                return ResultState.Forbidden(throwable = Throwable(errorResponse?.statusMessage?:""))
            }

            409->{
                var errorResponse: BaseErrorDataSourceApi? = null
                error.response()?.errorBody()?.let {
                    val jsonString = it.string() // important: read once
                    errorResponse = JSON.parseObject(jsonString, BaseErrorDataSourceApi::class.java)
                }
                return ResultState.Conflict(throwable = Throwable(errorResponse?.statusMessage?:""))

            }
            else -> {
                return ResultState.Error(throwable = error)
            }
        }

    } else {
        return ResultState.Error(throwable = error)
    }

}