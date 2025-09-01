package com.example.brewbuddy.domain.model

data class Drink(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val ingredients: List<String>,
    val price: String
) {

    val name: String get() = title
}
