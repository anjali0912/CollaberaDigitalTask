package com.example.data.localsource.recent.mapper

import com.example.domain.model.recent.RecentEntity
import com.example.domain.params.recent.RecentParams

class RecentMapperImpl : RecentMapper {
    override fun paramsToEntity(entity: RecentParams): RecentEntity {
        return with(entity) {
            RecentEntity(
                id = null,
                tempMax = this.tempMax,
                tempMin = this.tempMin,
                name = this.name,
                country = this.country,
                sunrise = this.sunrise,
                sunset = this.sunset
            )
        }
    }
}