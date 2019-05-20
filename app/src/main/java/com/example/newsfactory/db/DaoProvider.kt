package com.example.newsfactory.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsfactory.model.News

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class DaoProvider : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    companion object {
        private var instance: DaoProvider? = null
        fun getInstance(context: Context): DaoProvider {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context. applicationContext,
                    DaoProvider::class.java,
                    "NewsDb"
                ).allowMainThreadQueries().build()
            }
            return instance as DaoProvider
        }
    }
}