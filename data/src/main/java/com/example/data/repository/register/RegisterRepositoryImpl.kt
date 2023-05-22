package com.example.data.repository.register

import androidx.lifecycle.LiveData
import com.example.data.localsource.register.RegisterLocalSource
import com.example.domain.model.register.UserEntity
import com.example.domain.params.register.RegisterParams
import com.example.domain.repository.register.RegisterRepository

class RegisterRepositoryImpl(private val source: RegisterLocalSource) : RegisterRepository {
    override suspend fun addUser(entity: RegisterParams) = source.addUser(entity)

    override fun getUser(): LiveData<List<UserEntity>> = source.getUser()

    override fun getUserByEmailAndPassword(email: String, password: String) =
        source.getUserByEmailAndPassword(email, password)
}