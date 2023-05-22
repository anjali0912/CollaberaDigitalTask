package com.example.data.repository.recent

import androidx.lifecycle.LiveData
import com.example.data.localsource.recent.RecentLocalSource
import com.example.domain.model.recent.RecentEntity
import com.example.domain.params.recent.RecentParams
import com.example.domain.repository.recent.RecentRepository

class RecentRepositoryImpl(private val source: RecentLocalSource) : RecentRepository {
    override suspend fun addRecentLocation(entity: RecentParams) = source.addRecentLocation(entity)

    override fun getAllRecentLocation(): LiveData<List<RecentEntity>> =
        source.getAllRecentLocation()
}