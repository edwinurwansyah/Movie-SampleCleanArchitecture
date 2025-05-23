package com.edwinnrw.moviecleanarchitecture.presentation.common

import com.edwinnrw.moviecleanarchitecture.data.entities.BaseErrorDataSourceApi
import com.edwinnrw.moviecleanarchitecture.domain.common.ResultState
import com.google.common.reflect.TypeToken
import kotlinx.serialization.json.Json

import retrofit2.HttpException


fun getResultStateError(error: Throwable) : ResultState {
    return if (error is HttpException) {
        when (error.code()) {
            504 -> {
                ResultState.NoConnection(throwable = Throwable("No Connection"))
            }
            400 -> {
                val errorBody = error.response()?.errorBody()?.string()
                val errorResponse: BaseErrorDataSourceApi? = try {
                    errorBody?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
                } catch (e: Exception) {
                    null
                }
                return ResultState.BadRequest(throwable = Throwable(errorResponse?.statusMessage?:""))

            }
            401 -> {
                val errorBody = error.response()?.errorBody()?.string()
                val errorResponse: BaseErrorDataSourceApi? = try {
                    errorBody?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
                } catch (e: Exception) {
                    null
                }
                return ResultState.Unauthorized(throwable = Throwable(errorResponse?.statusMessage?:""))

            }
            404 -> {
                return ResultState.NotFound(throwable = Throwable("Not Found"))

            }
            403->{
                val errorBody = error.response()?.errorBody()?.string()
                val errorResponse: BaseErrorDataSourceApi? = try {
                    errorBody?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
                } catch (e: Exception) {
                    null
                }
                return ResultState.Forbidden(throwable = Throwable(errorResponse?.statusMessage?:""))
            }

            409->{
                val errorBody = error.response()?.errorBody()?.string()
                val errorResponse: BaseErrorDataSourceApi? = try {
                    errorBody?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
                } catch (e: Exception) {
                    null
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