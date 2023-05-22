package com.example.collaberadigitaltask.register

import com.example.core.BaseEvent

sealed class RegisterEvents : BaseEvent {
    object OnSuccess : RegisterEvents()
    object OnFailure : RegisterEvents()
}