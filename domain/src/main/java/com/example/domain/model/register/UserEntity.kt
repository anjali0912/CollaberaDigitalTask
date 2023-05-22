package com.example.domain.model.register

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val email: String = "",
    val password: String = ""
) : Parcelable