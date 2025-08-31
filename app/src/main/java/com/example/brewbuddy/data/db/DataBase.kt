package com.example.brewbuddy.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.brewbuddy.data.orderdb.OrderDao
import com.example.brewbuddy.data.orderdb.OrderEntity
import com.example.brewbuddy.data.room.FavoriteDao
import com.example.brewbuddy.data.room.FavoriteEntity

@Database(
    entities = [CoffeeEntity::class, FavoriteEntity::class, OrderEntity::class],
    version = 4,
    exportSchema = false
)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun orderDao(): OrderDao
    companion object {
        @Volatile
        private var INSTANCE: CoffeeDatabase? = null

        fun getDatabase(context: Context): CoffeeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoffeeDatabase::class.java,
                    "coffee_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
