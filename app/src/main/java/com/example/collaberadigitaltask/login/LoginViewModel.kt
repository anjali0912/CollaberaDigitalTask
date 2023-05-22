package com.example.collaberadigitaltask.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.domain.params.register.RegisterParams
import com.example.domain.usecase.register.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application,
    data: LoginData,
    val registerUseCase: RegisterUseCase
) : BaseViewModel<LoginData, LoginEvents>(application, data) {

    fun login(params: RegisterParams) =
        viewModelScope.launch(Dispatchers.IO) {
            val result = registerUseCase.getUserByEmailAndPassword(
                email = params.email,
                password = params.password
            )
            if (result == null) {
                updateEvent(LoginEvents.OnFailure)
            } else {
                updateEvent(LoginEvents.OnSuccess)
            }
        }
}