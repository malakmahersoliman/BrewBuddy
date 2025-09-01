package com.example.brewbuddy.domain.model

data class Favorite(
    val id: Int,
    val name: String,
    val image: String?,
    val description: String?,
    val price: String = "0.0"
)
