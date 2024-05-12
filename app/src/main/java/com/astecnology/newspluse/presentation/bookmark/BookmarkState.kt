package com.astecnology.newspluse.presentation.bookmark

import com.astecnology.newspluse.domain.model.Article

/**
 * Data class representing the state of the Bookmark screen.
 * @param articles The list of bookmarked articles.
 */
data class BookmarkState(
    val articles: List<Article> = emptyList()
)