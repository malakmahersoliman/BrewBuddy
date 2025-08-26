package com.example.brewbuddy.domain.repo

import com.example.brewbuddy.data.entities.Coffee

interface CoffeeRepo {
    suspend fun getColdCoffee(): List<Coffee>
}