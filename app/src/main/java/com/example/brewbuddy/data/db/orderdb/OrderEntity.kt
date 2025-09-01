package com.example.brewbuddy.data.db.orderdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val quantity: Int,
    val date: Date,
    val description: String?,
    val imageUrl: String
)