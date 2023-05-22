package com.example.data.localsource.recent

import androidx.lifecycle.LiveData
import com.example.data.localsource.recent.database.RecentDao
import com.example.data.localsource.recent.mapper.RecentMapper
import com.example.domain.model.recent.RecentEntity
import com.example.domain.params.recent.RecentParams

class RecentLocalSourceImpl(
    private val recentDao: RecentDao,
    private val mapper: RecentMapper,
) : RecentLocalSource {
    override suspend fun addRecentLocation(entity: RecentParams) =
        recentDao.addRecentLocation(mapper.paramsToEntity(entity))

    override fun getAllRecentLocation(): LiveData<List<RecentEntity>> =
        recentDao.getAllRecentLocation()
}