package com.example.core

sealed class Error {
    data class RemoteError(val code : Int = VALUE_ZERO, val message : String = EMPTY_STRING) : Error()
    data class DBError(val error : String = EMPTY_STRING) : Error()
    object NoConnectivityError : Error()
    data class GenericError(val message : String = EMPTY_STRING) : Error()
}