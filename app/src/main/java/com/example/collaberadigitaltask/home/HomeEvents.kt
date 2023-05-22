package com.example.collaberadigitaltask.home

import com.example.core.BaseEvent

sealed class HomeEvents : BaseEvent {
    object OnSuccess : HomeEvents()
    object OnFailure : HomeEvents()
}