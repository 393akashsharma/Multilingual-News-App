package com.astecnology.newspluse.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.astecnology.newspluse.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel class for the Home screen.
 * @param newsUseCases Use case class providing access to news-related functionalities.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {

    // Fetch news articles from specified sources and cache the results in the ViewModel scope
    val news = newsUseCases.getNews(source = listOf("bbc-news", "abc-news", "al-jazeera-english"))
        .cachedIn(viewModelScope)
}