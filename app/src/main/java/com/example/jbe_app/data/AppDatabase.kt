package com.example.jbe_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postModelDao(): PostModelDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "your_database_name"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
