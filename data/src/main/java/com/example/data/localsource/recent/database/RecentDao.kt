package com.example.data.localsource.recent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.recent.RecentEntity

@Dao
interface RecentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecentLocation(entity: RecentEntity)

    @Query("SELECT * FROM RecentEntity")
    fun getAllRecentLocation(): LiveData<List<RecentEntity>>
}