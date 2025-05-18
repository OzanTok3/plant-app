package com.ozantok.plantapp.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozantok.plantapp.data.local.dao.SearchQueryDao
import com.ozantok.plantapp.data.local.entity.SearchQueryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dao: SearchQueryDao
) : ViewModel() {

    fun saveQuery(query: String) {
        viewModelScope.launch {
            dao.insertQuery(SearchQueryEntity(query = query))
        }
    }

    fun getAllQueries() = dao.getAllQueries()
}