package com.example.domain.params.register

import org.junit.Assert
import org.junit.jupiter.api.Test

class RegisterParamsTest {

    private val registerParams = RegisterParams(
        email = EMAIL,
        password = PASSWORD
    )

    @Test
    fun getEmailTest() {
        Assert.assertEquals(EMAIL, registerParams.email)
    }

    @Test
    fun getPasswordTest() {
        Assert.assertEquals(PASSWORD, registerParams.password)
    }

    companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}