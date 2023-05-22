package com.example.domain.usecase.register

import androidx.lifecycle.LiveData
import com.example.domain.model.register.UserEntity
import com.example.domain.params.register.RegisterParams
import com.example.domain.repository.register.RegisterRepository

class RegisterUseCaseImpl(private val repository: RegisterRepository) :
    RegisterUseCase {
    override suspend fun addUser(entity: RegisterParams) = repository.addUser(entity)

    override fun getUser(): LiveData<List<UserEntity>> = repository.getUser()

    override fun getUserByEmailAndPassword(email: String, password: String) =
        repository.getUserByEmailAndPassword(email, password)
}