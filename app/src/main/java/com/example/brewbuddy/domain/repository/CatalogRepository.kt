package com.example.brewbuddy.domain.repository

import com.example.brewbuddy.domain.model.Drink
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
    fun getCatalog(): Flow<List<Drink>>
}
