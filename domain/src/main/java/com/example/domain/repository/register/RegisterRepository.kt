package com.example.domain.repository.register

import androidx.lifecycle.LiveData
import com.example.domain.model.register.UserEntity
import com.example.domain.params.register.RegisterParams

interface RegisterRepository {
    suspend fun addUser(entity: RegisterParams)
    fun getUser(): LiveData<List<UserEntity>>
    fun getUserByEmailAndPassword(email: String, password: String): UserEntity?
}