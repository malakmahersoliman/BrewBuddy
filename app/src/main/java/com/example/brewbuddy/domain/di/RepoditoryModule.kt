package com.example.brewbuddy.domain.di

import com.example.brewbuddy.data.repository.CoffeeRepoImpl
import com.example.brewbuddy.domain.repo.CoffeeRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCoffeeRepo(
        repoImpl: CoffeeRepoImpl
    ): CoffeeRepo
}