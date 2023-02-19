package com.example.magiccards.data.network

sealed class ApiResponse<T: Any> {
    class Success<T: Any>(val data: T) : ApiResponse<T>()
    class Error<T: Any>(val code: Int, val message: String) : ApiResponse<T>()
}

