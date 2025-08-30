package com.example.brewbuddy.data.mappers

import com.example.brewbuddy.data.db.orderdb.OrderEntity
import com.example.brewbuddy.domain.model.Order

fun OrderEntity.toDomain(): Order {
    return Order(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        date = this.date,
        imageUrl = this.imageUrl
    )


}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        date = this.date,
        imageUrl = this.imageUrl
    )
}