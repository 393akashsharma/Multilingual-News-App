package com.astecnology.newspluse.presentation.search

import androidx.paging.PagingData
import com.astecnology.newspluse.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Data class representing the state of the search screen.
 * @param searchQuery The current search query entered by the user.
 * @param articles Flow representing the paginated list of articles resulting from the search.
 */
data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)