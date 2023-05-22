package com.example.domain.model.recent

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class RecentEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val tempMax: String = "",
    val tempMin: String = "",
    val name: String = "",
    val country: String = "",
    val sunrise: String = "",
    val sunset: String = ""
) : Parcelable