package com.astecnology.newspluse.presentation.search

/**
 * Sealed class representing events related to searching.
 */
sealed class SearchEvent {

    /**
     * Event to update the search query.
     * @property searchQuery The updated search query.
     */
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    /**
     * Event to initiate the search for news.
     */
    object SearchNews : SearchEvent()
}