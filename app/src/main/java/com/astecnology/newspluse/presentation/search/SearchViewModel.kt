package com.astecnology.newspluse.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.astecnology.newspluse.domain.usecases.news.NewsUseCases
import com.astecnology.newspluse.presentation.search.SearchEvent
import com.astecnology.newspluse.presentation.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel responsible for handling search functionality in the application.
 * @param newsUseCases Use cases for news-related operations.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {

    // Mutable state representing the current state of the search screen
    private val _state = mutableStateOf(SearchState())
    // Immutable state exposed to observe changes in the search screen state
    val state: State<SearchState> = _state

    /**
     * Function to handle events triggered by the search screen.
     * @param event The event representing user actions or screen transitions.
     */
    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                // Update the search query in the state
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                // Initiate the search operation
                searchNews()
            }
        }
    }

    /**
     * Function to perform the news search operation based on the current search query.
     */
    private fun searchNews() {
        // Retrieve articles matching the search query and specified news sources
        val articles = newsUseCases.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        // Update the state with the retrieved articles
        _state.value = state.value.copy(articles = articles)
    }
}