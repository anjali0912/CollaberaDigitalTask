package com.example.domain.usecase.register

import androidx.lifecycle.LiveData
import com.example.domain.model.register.UserEntity
import com.example.domain.params.register.RegisterParams

interface RegisterUseCase {
    suspend fun addUser(entity: RegisterParams)
    fun getUser(): LiveData<List<UserEntity>>
    fun getUserByEmailAndPassword(email: String, password: String): UserEntity?
}