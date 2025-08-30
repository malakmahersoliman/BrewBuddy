package com.example.brewbuddy.domain.model

data class Order(
    val id: Int,
    val name: String,
    val quantity: Int,
    val date: String,
    val imageUrl: String

)