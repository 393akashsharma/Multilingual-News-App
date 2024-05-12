package com.astecnology.newspluse.domain.usecases.news

import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case class for selecting all articles.
 * @param newsRepository The NewsRepository instance for accessing article-related operations.
 */
class SelectArticles(private val newsRepository: NewsRepository) {

    /**
     * Invokes the use case to select all articles.
     * @return A Flow emitting a list of articles stored in the local database.
     */
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}