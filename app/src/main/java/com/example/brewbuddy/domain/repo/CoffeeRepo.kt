package com.example.brewbuddy.domain.repo

import com.example.brewbuddy.domain.model.Coffee

interface CoffeeRepo {
    suspend fun getColdCoffee(): List<Coffee>
    suspend fun getHotCoffee(): List<Coffee>
}