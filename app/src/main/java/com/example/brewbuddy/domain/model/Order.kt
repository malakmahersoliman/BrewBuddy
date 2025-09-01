package com.example.brewbuddy.domain.model

import java.util.Date

data class Order(
    val id: Int,
    val name: String,
    val quantity: Int,
    val date: Date,
    val description:String?,
    val imageUrl: String
){
    val price: String = "$5.00"
}