package com.astecnology.newspluse.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.astecnology.newspluse.data.local.NewsDao
import com.astecnology.newspluse.data.remote.NewsApi
import com.astecnology.newspluse.data.remote.NewsPagingSource
import com.astecnology.newspluse.data.remote.SearchNewsPagingSource
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class NewsRepositoryImpl(private val newsApi: NewsApi, private val newsDao: NewsDao) :
    NewsRepository {

    /**
     * Retrieves news articles from remote sources based on provided sources.
     * @param sources The list of sources from which to retrieve news articles.
     * @return A Flow emitting PagingData containing news articles.
     */
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }


    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }


    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }


    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }


    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }


    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}