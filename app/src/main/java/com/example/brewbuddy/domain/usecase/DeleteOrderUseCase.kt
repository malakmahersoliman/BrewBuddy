package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.data.orderdb.OrderEntity
import com.example.brewbuddy.domain.model.Order
import com.example.brewbuddy.domain.repository.OrderRepository
import javax.inject.Inject

class DeleteOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(order: Order) = repository.deleteOrder(order)
}