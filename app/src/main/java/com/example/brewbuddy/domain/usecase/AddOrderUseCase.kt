package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.data.orderdb.OrderEntity
import com.example.brewbuddy.domain.repository.OrderRepository
import javax.inject.Inject

class AddOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(order: OrderEntity) = repository.insertOrder(order)
}
