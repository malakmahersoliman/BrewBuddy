package com.example.brewbuddy.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String?,
    val description: String?,
    val price: String
)
