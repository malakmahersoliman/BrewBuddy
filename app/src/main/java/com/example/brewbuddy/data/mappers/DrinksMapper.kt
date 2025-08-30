package com.example.brewbuddy.data.mappers

import com.example.brewbuddy.data.entities.CoffeeModel
import com.example.brewbuddy.domain.model.Coffee

fun CoffeeModel.CoffeeModelItem.toCoffee(): Coffee {
    return Coffee(
        id = id ?: 0,
        title = title ?: "",
        description = description ?: "",
        ingredients = ingredients?.filterNotNull() ?: emptyList(),
        image = image ?: ""
    )
}
