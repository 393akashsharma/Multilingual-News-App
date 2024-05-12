package com.astecnology.newspluse.domain.usecases.news

import androidx.paging.PagingData
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case class for retrieving news articles.
 * @param newsRepository The NewsRepository instance for accessing article-related operations.
 */
class GetNews(private val newsRepository: NewsRepository) {

    operator fun invoke(source: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = source)
    }
}