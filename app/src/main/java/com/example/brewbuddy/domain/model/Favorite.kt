package com.example.brewbuddy.domain.model

data class Favorite(
    val id: Int,
    val name: String,
    val image: String?,
    val description: String?,
    val price: Double = 0.0
)
