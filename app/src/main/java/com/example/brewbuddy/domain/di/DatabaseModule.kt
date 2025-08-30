package com.example.brewbuddy.domain.di

import android.content.Context
import androidx.room.Room
import com.example.brewbuddy.data.db.CoffeeDatabase
import com.example.brewbuddy.data.db.CoffeeDao
import com.example.brewbuddy.data.orderdb.OrderDao
import com.example.brewbuddy.data.room.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCoffeeDatabase(@ApplicationContext context: Context): CoffeeDatabase {
        return Room.databaseBuilder(
            context,
            CoffeeDatabase::class.java,
            "coffee_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCoffeeDao(db: CoffeeDatabase): CoffeeDao = db.coffeeDao()

    @Provides
    fun provideFavoriteDao(db: CoffeeDatabase): FavoriteDao = db.favoriteDao()
    @Provides
    fun provideOrderDao(db: CoffeeDatabase): OrderDao = db.orderDao()
}
