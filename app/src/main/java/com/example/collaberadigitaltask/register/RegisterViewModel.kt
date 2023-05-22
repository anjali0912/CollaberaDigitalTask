package com.example.collaberadigitaltask.register

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.domain.params.register.RegisterParams
import com.example.domain.usecase.register.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    application: Application,
    data: RegisterData,
    val registerUseCase: RegisterUseCase
) : BaseViewModel<RegisterData, RegisterEvents>(application, data) {

    fun addUser(params: RegisterParams) =
        viewModelScope.launch(Dispatchers.IO) {
            registerUseCase.addUser(params)
        }
}