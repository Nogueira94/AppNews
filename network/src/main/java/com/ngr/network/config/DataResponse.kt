package com.ngr.network.config

sealed class DataResponse<out T> {
    data class Success<out T>(val data: T) : DataResponse<T>()
    data class Failure(val error: Throwable) : DataResponse<Nothing>()
}