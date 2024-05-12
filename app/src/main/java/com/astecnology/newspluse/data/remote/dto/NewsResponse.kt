package com.astecnology.newspluse.data.remote.dto

import com.astecnology.newspluse.domain.model.Article


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)