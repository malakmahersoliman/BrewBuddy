package com.example.brewbuddy.domain.di

import com.example.brewbuddy.data.repository.UserRepositoryImpl
import com.example.brewbuddy.data.repository.CoffeeRepositoryImpl
import com.example.brewbuddy.data.repository.OrderRepositoryImpl
import com.example.brewbuddy.data.repository.FavoritesRepositoryImpl
import com.example.brewbuddy.domain.repository.UserRepository
import com.example.brewbuddy.domain.repository.CoffeeRepository
import com.example.brewbuddy.domain.repository.OrderRepository
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
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindCoffeeRepository(
        repoImpl: CoffeeRepositoryImpl
    ): CoffeeRepository
    
    @Binds
    @Singleton
    abstract fun bindOrderRepository(
        impl: OrderRepositoryImpl
    ): OrderRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        repoImpl: FavoritesRepositoryImpl
    ): FavoritesRepository
}
