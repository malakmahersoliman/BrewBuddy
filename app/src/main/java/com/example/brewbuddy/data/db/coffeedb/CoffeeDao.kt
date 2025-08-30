package com.example.brewbuddy.data.db.coffeedb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoffeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffee(coffee: CoffeeEntity)

    @Query("SELECT * FROM coffee_table")
    suspend fun getAllCoffee(): List<CoffeeEntity>
}