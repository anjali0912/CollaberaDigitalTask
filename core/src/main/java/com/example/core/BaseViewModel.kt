package com.example.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel<D : BaseData, E : BaseEvent>(
    application: Application,
    protected val data: D
) : AndroidViewModel(application) {

    val liveEvents: SingleLiveEvent<E> = SingleLiveEvent()

    infix fun updateEvent(event: E?) {
        event?.let { liveEvents.postValue(event) }
    }
}