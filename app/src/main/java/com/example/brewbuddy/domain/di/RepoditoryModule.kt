package com.example.brewbuddy.domain.di

import com.example.brewbuddy.data.repository.CatalogRepositoryImpl
import com.example.brewbuddy.data.repository.CoffeeRepoImpl
import com.example.brewbuddy.data.repository.FavoritesRepositoryImpl
import com.example.brewbuddy.data.repository.UserRepositoryImpl
import com.example.brewbuddy.domain.repo.CoffeeRepo
import com.example.brewbuddy.domain.repo.UserRepo
import com.example.brewbuddy.domain.repository.CatalogRepository
import com.example.brewbuddy.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoffeeRepo(
        repoImpl: CoffeeRepoImpl
    ): CoffeeRepo

    @Binds
    @Singleton
    abstract fun bindCatalogRepository(
        impl: CatalogRepositoryImpl
    ): CatalogRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        repoImpl: FavoritesRepositoryImpl
    ): FavoritesRepository
    @Binds
    @Singleton
    abstract fun bindUserRepo(impl: UserRepositoryImpl): UserRepo
}
