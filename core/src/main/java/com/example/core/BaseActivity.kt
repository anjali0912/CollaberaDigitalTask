package com.example.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<E : BaseEvent, D : BaseData, out VM : BaseViewModel<D, E>>(
    viewModelClazz: KClass<VM>
) : AppCompatActivity(), EventListener {

    protected val viewModel: VM by viewModel(clazz = viewModelClazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.liveEvents.observeForever {
            eventUpdated(it)
        }
        getInitialData()
    }

    open fun getInitialData() {}

    abstract override fun eventUpdated(event: BaseEvent)
}