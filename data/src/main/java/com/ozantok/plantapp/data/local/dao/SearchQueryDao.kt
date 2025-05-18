package com.ozantok.plantapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ozantok.plantapp.data.local.entity.SearchQueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchQueryDao {

    @Insert
    suspend fun insertQuery(query: SearchQueryEntity)

    @Query("SELECT * FROM search_queries ORDER BY timestamp DESC")
    fun getAllQueries(): Flow<List<SearchQueryEntity>>
}
