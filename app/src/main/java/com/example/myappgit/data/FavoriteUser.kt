package com.example.myappgit.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize


@Entity(tableName = "favorite_user")
data class FavoriteUser(
    @PrimaryKey val id: Int,
    val username: String,
    val avatarUrl: String,
)
