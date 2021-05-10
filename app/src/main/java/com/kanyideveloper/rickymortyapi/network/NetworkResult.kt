package com.kanyideveloper.rickymortyapi.network

import java.lang.Exception

sealed class NetworkResult<out T> {
    data class OnFailure(val exception: Exception) : NetworkResult<Nothing>()
    data class OnSuccess<out T>(val value : T) : NetworkResult<T>()
}