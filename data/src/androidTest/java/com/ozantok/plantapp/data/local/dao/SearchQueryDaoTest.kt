package com.ozantok.plantapp.data.local.dao


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ozantok.plantapp.data.local.AppDatabase
import com.ozantok.plantapp.data.local.dao.SearchQueryDao
import com.ozantok.plantapp.data.local.entity.SearchQueryEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class SearchQueryDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: SearchQueryDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.searchQueryDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertQuery_and_getAllQueries_returnsCorrectData() = runTest {

        val query = SearchQueryEntity(query = "plant")
        dao.insertQuery(query)


        val results = dao.getAllQueries().first()


        Assert.assertEquals(1, results.size)
        Assert.assertEquals("plant", results.first().query)
    }
}