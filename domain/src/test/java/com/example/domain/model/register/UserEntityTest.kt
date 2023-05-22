package com.example.domain.model.register

import org.junit.Assert
import org.junit.jupiter.api.Test

internal class UserEntityTest {

    private val userEntity = UserEntity(
        email = EMAIL,
        password = PASSWORD
    )
    private val userEmptyEntity = UserEntity()

    @Test
    fun getEmailTest() {
        Assert.assertEquals(EMAIL, userEntity.email)
        Assert.assertEquals("", userEmptyEntity.email)
    }

    @Test
    fun getPasswordTest() {
        Assert.assertEquals(PASSWORD, userEntity.password)
        Assert.assertEquals("", userEmptyEntity.password)
    }

    @Test
    fun getIdTest() {
        Assert.assertEquals(null, userEntity.id)
        Assert.assertEquals(null, userEmptyEntity.id)
    }

    companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}