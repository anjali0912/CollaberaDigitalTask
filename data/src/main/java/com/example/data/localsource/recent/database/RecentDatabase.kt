package com.example.data.localsource.recent.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.model.recent.RecentEntity

const val RECENT_DATABASE = "recent_database"

@Database(entities = [RecentEntity::class], version = 1, exportSchema = false)
abstract class RecentDatabase : RoomDatabase() {

    abstract fun getRecentDao(): RecentDao

    companion object {
        // Volatile annotation means any change to this field
        // are immediately visible to other threads.
        @Volatile
        private var INSTANCE: RecentDatabase? = null

        fun getDatabase(context: Context): RecentDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            // here synchronised used for blocking the other thread
            // from accessing another while in a specific code execution.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecentDatabase::class.java,
                    RECENT_DATABASE
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}