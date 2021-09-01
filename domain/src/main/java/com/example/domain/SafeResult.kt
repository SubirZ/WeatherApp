package com.example.domain

import retrofit2.Response

sealed class SafeResult<out T> {
    data class Success<T>(val data: T?) : SafeResult<T>()
    data class Failure<T>(val errorResponse: Response<T>? = null) : SafeResult<T>()
}