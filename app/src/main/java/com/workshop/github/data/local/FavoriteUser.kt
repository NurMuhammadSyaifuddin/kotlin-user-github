package com.workshop.github.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_user")
data class FavoriteUser(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatar_url: String
): Serializable
