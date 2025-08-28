package com.example.brewbuddy.data.mappers

import com.example.brewbuddy.data.room.FavoriteEntity
import com.example.brewbuddy.domain.model.Drink
import com.example.brewbuddy.domain.model.Favorite

// Entity → Domain
fun FavoriteEntity.toDomain(): Favorite {
    return Favorite(
        id = this.id,
        name = this.title,
        image = this.image,
        description = this.description ?: "",
        price = this.price
    )
}

// Domain Drink → Entity
fun Drink.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = this.id,
        title = this.name,
        image = this.image?: "",
        description = this.ingredients.joinToString(", "),
        price = this.price
    )
}
