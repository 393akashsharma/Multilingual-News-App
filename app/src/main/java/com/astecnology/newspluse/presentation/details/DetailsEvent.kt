package com.astecnology.newspluse.presentation.details

import com.astecnology.newspluse.domain.model.Article

/**
 * Sealed class representing events in the details screen.
 */
sealed class DetailsEvent {
    /**
     * Event for upserting or deleting an article.
     * @param article The article to upsert or delete.
     */
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    /**
     * Event for removing side events.
     */
    object RemoveSideEvent : DetailsEvent()
}