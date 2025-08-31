package com.example.brewbuddy.domain.model

data class Coffee(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val image: String
) {
    var price: String = "$5.00"
    val name: String get() = title
}