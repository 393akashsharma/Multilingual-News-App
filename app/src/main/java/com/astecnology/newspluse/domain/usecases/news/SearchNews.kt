package com.astecnology.newspluse.domain.usecases.news

import androidx.paging.PagingData
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case class for searching news articles.
 * @param newsRepository The NewsRepository instance for accessing article-related operations.
 */
class SearchNews(private val newsRepository: NewsRepository) {

    /**
     * Invokes the use case to search for news articles.
     * @param searchQuery The search query used to search for news articles.
     * @param sources The list of sources to search for news articles.
     * @return A Flow emitting PagingData containing searched news articles.
     */
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}