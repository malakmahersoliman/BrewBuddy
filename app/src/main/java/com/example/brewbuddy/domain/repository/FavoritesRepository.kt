package com.example.brewbuddy.domain.repository

import com.example.brewbuddy.domain.model.Drink
import com.example.brewbuddy.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    val favorites: Flow<List<Favorite>>
    suspend fun add(drink: Drink)
    suspend fun removeById(id: Int)
}
