package com.astecnology.newspluse.domain.usecases.news

/**
 * Data class representing the use cases related to news operations.
 * @param getNews The use case for retrieving news articles.
 * @param searchNews The use case for searching news articles.
 * @param upsertArticle The use case for inserting or updating an article.
 * @param deleteArticle The use case for deleting an article.
 * @param selectArticles The use case for selecting all articles.
 * @param selectArticle The use case for selecting a specific article by URL.
 */
data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle : SelectArticle
)