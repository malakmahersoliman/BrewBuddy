package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.room.FavoriteDao
import com.example.brewbuddy.data.room.FavoriteEntity
import com.example.brewbuddy.domain.model.Drink
import com.example.brewbuddy.domain.model.Favorite
import com.example.brewbuddy.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// Mapper: Entity → Domain
private fun FavoriteEntity.toDomain(): Favorite {
    return Favorite(
        id = this.id,
        name = this.title,
        image = this.image,
        description = this.description ?: "",
        price = this.price
    )
}

// Mapper: Drink → Entity
private fun Drink.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = this.id,
        title = this.name,
        image = this.image?: "",
        description = this.ingredients.joinToString(", "),
        price = this.price
    )
}

@Singleton
class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoritesRepository {

    override val favorites: Flow<List<Favorite>> =
        dao.getFavorites().map { list -> list.map { it.toDomain() } }

    override suspend fun add(drink: Drink) {
        dao.addFavorite(drink.toEntity())
    }

    override suspend fun removeById(id: Int) {
        dao.removeFavorite(id)
    }
}
