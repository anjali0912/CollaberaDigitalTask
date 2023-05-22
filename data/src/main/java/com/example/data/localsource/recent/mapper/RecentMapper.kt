package com.example.data.localsource.recent.mapper

import com.example.domain.model.recent.RecentEntity
import com.example.domain.params.recent.RecentParams

interface RecentMapper {
    fun paramsToEntity(entity: RecentParams): RecentEntity
}