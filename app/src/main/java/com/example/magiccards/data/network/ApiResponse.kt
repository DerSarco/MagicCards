package com.example.magiccards.data.network

import com.example.magiccards.data.entities.LocalMagicCard

sealed class ApiResponse {
    class Success(val data: List<LocalMagicCard>) : ApiResponse()
    class SuccessSingleCard(val data: LocalMagicCard) : ApiResponse()
    class Error(val code: Int, val message: String) : ApiResponse()
}
