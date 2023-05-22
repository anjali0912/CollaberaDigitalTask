package com.example.data.localsource.register.mapper

import com.example.domain.model.register.UserEntity
import com.example.domain.params.register.RegisterParams

class RegisterMapperImpl : RegisterMapper {
    override fun paramsToEntity(entity: RegisterParams): UserEntity {
        return with(entity) {
            UserEntity(
                id = null,
                email = this.email,
                password = this.password,
            )
        }
    }
}