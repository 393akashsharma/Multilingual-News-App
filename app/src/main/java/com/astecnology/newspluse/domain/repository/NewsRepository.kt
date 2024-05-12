package com.astecnology.newspluse.domain.repository

import androidx.paging.PagingData
import com.astecnology.newspluse.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing news data from remote and local sources.
 */
interface NewsRepository {
    /**
     * Retrieves news articles from remote sources based on provided sources.
     * @param sources The list of sources from which to retrieve news articles.
     * @return A Flow emitting PagingData containing news articles.
     */
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    /**
     * Searches for news articles based on the provided search query and sources.
     * @param searchQuery The search query to be used for searching news articles.
     * @param sources The list of sources to search for news articles.
     * @return A Flow emitting PagingData containing searched news articles.
     */
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
    /**
     * Inserts or replaces an article in the local database.
     * @param article The article to be inserted or replaced.
     */
    suspend fun upsertArticle(article: Article)
    /**
     * Deletes an article from the local database.
     * @param article The article to be deleted.
     */
    suspend fun deleteArticle(article: Article)
    /**
     * Retrieves all articles stored in the local database.
     * @return A Flow emitting a list of articles stored in the local database.
     */
    fun selectArticles(): Flow<List<Article>>
    /**
     * Retrieves an article from the local database based on its URL.
     * @param url The URL of the article to retrieve.
     * @return The article corresponding to the given URL, if it exists; otherwise, null.
     */
    suspend fun selectArticle(url: String): Article?
}