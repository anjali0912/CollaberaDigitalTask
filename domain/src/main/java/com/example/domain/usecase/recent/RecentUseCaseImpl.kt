package com.example.domain.usecase.recent

import androidx.lifecycle.LiveData
import com.example.domain.model.recent.RecentEntity
import com.example.domain.params.recent.RecentParams
import com.example.domain.repository.recent.RecentRepository

class RecentUseCaseImpl(private val repository: RecentRepository) :
    RecentUseCase {
    override suspend fun addRecentLocation(entity: RecentParams) =
        repository.addRecentLocation(entity)

    override fun getAllRecentLocation(): LiveData<List<RecentEntity>> =
        repository.getAllRecentLocation()
}