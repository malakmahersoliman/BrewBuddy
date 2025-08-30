package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.mappers.toDomain
import com.example.brewbuddy.data.orderdb.OrderDao
import com.example.brewbuddy.data.orderdb.OrderEntity
import com.example.brewbuddy.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.example.brewbuddy.domain.model.Order
import com.example.brewbuddy.data.mappers.toEntity

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderRepository {

    override fun getAllOrders(): Flow<List<Order>> {
        return orderDao.getAllOrders().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order.toEntity())
    }

    override suspend fun deleteOrder(order: Order) {
        orderDao.deleteOrder(order.toEntity())
    }
}
