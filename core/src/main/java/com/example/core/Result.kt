package com.example.core

sealed class Result<out E, out R> {

    data class Success<out R>(val data: R) : Result<Nothing, R>()
    data class Failure<E>(val error: E) : Result<E, Nothing>()

    fun mapResult(success: (R) -> Any, failure: (E) -> Any) : Any =
        when (this) {
            is Success -> success(data)
            is Failure -> failure(error)
        }
}

