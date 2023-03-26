package com.knight.moonreaderdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LightNovel::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun bookDAO(): LightNovelDAO

    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, MainDatabase::class.java, "Main"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}