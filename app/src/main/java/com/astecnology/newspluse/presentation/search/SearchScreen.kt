package com.astecnology.newspluse.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.presentation.Dimens
import com.astecnology.newspluse.presentation.common.ArticleList
import com.astecnology.newspluse.presentation.common.SearchBar

/**
 * Composable function representing the screen for searching news.
 * @param state The current state of the search screen.
 * @param event The event handler for search-related events.
 * @param navigateToDetails Function to navigate to the details screen for a specific article.
 */
@Composable
fun SearchScreen(state: SearchState, event: (SearchEvent) -> Unit, navigateToDetails: (Article) -> Unit) {
    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(text = state.searchQuery, readOnly = false, onValueChange = {
            event(SearchEvent.UpdateSearchQuery(it))
        }, onSearch = { event(SearchEvent.SearchNews) })

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onClick = { navigateToDetails(it) })
        }
    }
}