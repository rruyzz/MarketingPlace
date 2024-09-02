package com.meli.core.common.uievent

sealed class UIEvent<T>(val data : T? = null, val message : String? = null) {
    class Loading<T>(val isLoading: Boolean ): UIEvent<T>()
    class Success<T>(data: T): UIEvent<T>(data = data)
    class Error<T>(message: String): UIEvent<T>(message = message)
}