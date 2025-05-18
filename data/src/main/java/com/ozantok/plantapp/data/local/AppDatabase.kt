package com.ozantok.plantapp.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozantok.plantapp.data.local.dao.SearchQueryDao
import com.ozantok.plantapp.data.local.entity.SearchQueryEntity

@Database(entities = [SearchQueryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchQueryDao(): SearchQueryDao
}