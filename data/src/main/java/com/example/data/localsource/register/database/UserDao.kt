package com.example.data.localsource.register.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.domain.model.register.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(entity: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE email= :userEmail AND password=:userPassword")
    fun getUserByEmailAndPassword(userEmail: String, userPassword: String): UserEntity?
}