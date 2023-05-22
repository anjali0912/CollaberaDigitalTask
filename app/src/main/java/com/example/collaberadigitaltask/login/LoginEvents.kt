package com.example.collaberadigitaltask.login

import com.example.core.BaseEvent

sealed class LoginEvents : BaseEvent {
    object OnSuccess : LoginEvents()
    object OnFailure : LoginEvents()
}