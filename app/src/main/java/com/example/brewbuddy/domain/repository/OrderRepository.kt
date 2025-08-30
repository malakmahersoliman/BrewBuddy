package com.example.brewbuddy.domain.repository

import com.example.brewbuddy.data.orderdb.OrderEntity
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllOrders(): Flow<List<OrderEntity>>
    suspend fun insertOrder(order: OrderEntity)
    suspend fun deleteOrder(order: OrderEntity)
}