package com.astecnology.newspluse.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.domain.usecases.news.NewsUseCases
import com.astecnology.newspluse.presentation.details.DetailsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing the details screen's logic.
 * It interacts with the [NewsUseCases] to handle article insertion and deletion,
 * and manages side effects, such as displaying success or error messages.
 *
 * @property newsUseCases An instance of [NewsUseCases] to interact with the domain layer.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {

    // Mutable state to hold side effects, such as success or error messages
    var sideEffect by mutableStateOf<String?>(null)
        private set

    /**
     * Function to handle events triggered from the details screen.
     * @param event DetailsEvent representing the user action/event.
     */
    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    // Check if the article exists
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null) {
                        // Insert the article if it doesn't exist
                        upsertArticle(event.article)
                    } else {
                        // Delete the article if it already exists
                        deleteArticle(event.article)
                    }
                }
            }

            is DetailsEvent.RemoveSideEvent -> {
                sideEffect = null
            }
        }
    }

    /**
     * Function to upsert an article.
     * @param article The article to be upserted.
     */
    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        // Set the side effect message to indicate successful article insertion
        sideEffect = "Article Saved"
    }

    /**
     * Function to delete an article.
     * @param article The article to be deleted.
     */
    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        // Set the side effect message to indicate successful article deletion
        sideEffect = "Article Delete"
    }
}