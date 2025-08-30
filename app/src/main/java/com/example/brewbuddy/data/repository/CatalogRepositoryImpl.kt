package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.local.FakeCatalog
import com.example.brewbuddy.domain.model.Drink
import com.example.brewbuddy.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogRepositoryImpl @Inject constructor(
) : CatalogRepository {


    override fun getCatalog(): Flow<List<Drink>> = flow {
        emit(FakeCatalog.allDrinks)
    }
}
