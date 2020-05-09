package com.edwinnrw.moviecleanarchitecture.presentation.common

import com.edwinnrw.moviecleanarchitecture.data.entities.BaseErrorDataSourceApi
import com.edwinnrw.moviecleanarchitecture.domain.common.ResultState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException


fun getResultStateError(error: Throwable) : ResultState {
    return if (error is HttpException) {
        when (error.code()) {
            504 -> {
                ResultState.NoConnection(throwable = Throwable("No Connection"))
            }
            400 -> {
                val type = object : TypeToken<BaseErrorDataSourceApi>() {}.type
                var errorResponse: BaseErrorDataSourceApi? = null
                error.response()?.errorBody()?.let {
                    errorResponse = Gson().fromJson(error.response()?.errorBody()?.charStream(), type)

                }
                return ResultState.BadRequest(throwable = Throwable(errorResponse?.statusMessage?:""))

            }
            401 -> {
                val type = object : TypeToken<BaseErrorDataSourceApi>() {}.type
                var errorResponse: BaseErrorDataSourceApi? = null
                error.response()?.errorBody()?.let {
                    errorResponse = Gson().fromJson(error.response()?.errorBody()?.charStream(), type)

                }
                return ResultState.Unauthorized(throwable = Throwable(errorResponse?.statusMessage?:""))

            }
            404 -> {
                return ResultState.NotFound(throwable = Throwable("Not Found"))

            }
            403->{
                val type = object : TypeToken<BaseErrorDataSourceApi>() {}.type
                var errorResponse: BaseErrorDataSourceApi? = null
                error.response()?.errorBody()?.let {
                    errorResponse = Gson().fromJson(error.response()?.errorBody()?.charStream(), type)

                }
                return ResultState.Forbidden(throwable = Throwable(errorResponse?.statusMessage?:""))
            }

            409->{
                val type = object : TypeToken<BaseErrorDataSourceApi>() {}.type
                var errorResponse: BaseErrorDataSourceApi? = null
                error.response()?.errorBody()?.let {
                    errorResponse = Gson().fromJson(error.response()?.errorBody()?.charStream(), type)

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