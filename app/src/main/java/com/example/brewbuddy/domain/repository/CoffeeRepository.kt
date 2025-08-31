package com.example.brewbuddy.domain.repository

import com.example.brewbuddy.domain.model.Coffee

interface CoffeeRepository {
    suspend fun getColdCoffee(): List<Coffee>
    suspend fun getHotCoffee(): List<Coffee>
}