package com.example.brewbuddy.data.entities

data class Coffee(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val image: String
) {
    var price: String = "$5.00"
}