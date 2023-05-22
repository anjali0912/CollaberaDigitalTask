package com.example.data.localsource.recent

import androidx.lifecycle.LiveData
import com.example.domain.model.recent.RecentEntity
import com.example.domain.params.recent.RecentParams

interface RecentLocalSource {
    suspend fun addRecentLocation(entity: RecentParams)
    fun getAllRecentLocation(): LiveData<List<RecentEntity>>
}