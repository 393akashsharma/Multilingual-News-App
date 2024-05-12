package com.astecnology.newspluse.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astecnology.newspluse.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the Bookmark screen.
 * @param newsUseCases The use cases related to news operations.
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    /**
     * Initializes the ViewModel by retrieving bookmarked articles.
     */
    init {
        getArticles()
    }

    /**
     * Retrieves bookmarked articles using the NewsUseCases and updates the state.
     */
    fun getArticles() {
        newsUseCases.selectArticles().onEach {
            _state.value = _state.value.copy(articles = it.asReversed())
        }.launchIn(viewModelScope)
    }
}