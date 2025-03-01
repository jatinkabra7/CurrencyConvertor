package com.jatinkabra.music.player.currencyconvertor.domain.model

sealed class NetworkResponse<T>(val data : T? = null, val message : String? = null) {
    class Success<T>(data: T? = null) : NetworkResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResponse<T>(data,message)
}