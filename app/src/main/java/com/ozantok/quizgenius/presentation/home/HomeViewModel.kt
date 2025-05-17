package com.ozantok.quizgenius.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozantok.quizgenius.domain.model.Category
import com.ozantok.quizgenius.domain.model.Question
import com.ozantok.quizgenius.domain.usecase.GetCategoriesUseCase
import com.ozantok.quizgenius.domain.usecase.GetQuestionsUseCase
import com.ozantok.quizgenius.presentation.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _uiState = MutableStateFlow(UIState.LOADING)
    val uiState: StateFlow<UIState> = _uiState

    fun fetchHomeData() {
        viewModelScope.launch {
            _uiState.value = UIState.LOADING
            try {
                val questions = getQuestionsUseCase()
                val categories = getCategoriesUseCase()
                _questions.value = questions
                _categories.value = categories
                _uiState.value = UIState.SUCCESS
            } catch (e: Exception) {
                _uiState.value = UIState.ERROR
            }
        }
    }
}
