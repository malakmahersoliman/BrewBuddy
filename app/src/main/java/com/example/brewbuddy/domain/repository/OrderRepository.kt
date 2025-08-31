package com.example.brewbuddy.domain.repository

import com.example.brewbuddy.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllOrders(): Flow<List<Order>>
    suspend fun insertOrder(order: Order)
    suspend fun deleteOrder(order: Order)
}