package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.data.orderdb.OrderEntity
import com.example.brewbuddy.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    operator fun invoke(): Flow<List<OrderEntity>> = repository.getAllOrders()
}