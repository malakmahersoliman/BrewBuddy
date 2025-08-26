package com.example.brewbuddy.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.brewbuddy.data.db.CoffeeEntity

@Dao
interface CoffeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffee(coffee: CoffeeEntity)

    @Query("SELECT * FROM coffee_table")
    suspend fun getAllCoffee(): List<CoffeeEntity>
}