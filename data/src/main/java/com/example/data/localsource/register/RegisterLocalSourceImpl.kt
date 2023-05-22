package com.example.data.localsource.register

import androidx.lifecycle.LiveData
import com.example.data.localsource.register.database.UserDao
import com.example.data.localsource.register.mapper.RegisterMapper
import com.example.domain.model.register.UserEntity
import com.example.domain.params.register.RegisterParams

class RegisterLocalSourceImpl(
    private val userDao: UserDao,
    private val mapper: RegisterMapper,
) : RegisterLocalSource {
    override suspend fun addUser(entity: RegisterParams) =
        userDao.addUser(mapper.paramsToEntity(entity))

    override fun getUser(): LiveData<List<UserEntity>> = userDao.getUser()

    override fun getUserByEmailAndPassword(email: String, password: String) =
        userDao.getUserByEmailAndPassword(email, password)
}