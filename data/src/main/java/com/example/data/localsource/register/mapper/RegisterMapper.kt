package com.example.data.localsource.register.mapper

import com.example.domain.model.register.UserEntity
import com.example.domain.params.register.RegisterParams

interface RegisterMapper {
    fun paramsToEntity(entity: RegisterParams): UserEntity
}