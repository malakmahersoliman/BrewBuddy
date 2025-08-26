package com.example.brewbuddy.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoffeeEntity::class], version = 1)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao

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
