package com.astecnology.newspluse.domain.usecases.news

import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.domain.repository.NewsRepository

/**
 * Use case class for inserting or updating an article.
 * @param newsRepository The NewsRepository instance for accessing article-related operations.
 */
class UpsertArticle(private val newsRepository: NewsRepository) {

    /**
     * Invokes the use case to insert or update an article.
     * @param article The article to be inserted or updated.
     */
    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}