package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.orderdb.OrderDao
import com.example.brewbuddy.data.orderdb.OrderEntity
import com.example.brewbuddy.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderRepository {

    override fun getAllOrders(): Flow<List<OrderEntity>> {
        return orderDao.getAllOrders()
    }

    override suspend fun insertOrder(order: OrderEntity) {
        orderDao.insertOrder(order)
    }

    override suspend fun deleteOrder(order: OrderEntity) {
        orderDao.deleteOrder(order)
    }
}
