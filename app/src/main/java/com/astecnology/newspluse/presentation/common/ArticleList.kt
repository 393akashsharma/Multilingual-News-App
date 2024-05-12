package com.astecnology.newspluse.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.presentation.Dimens

/**
 * Composable function for displaying a list of articles.
 * @param modifier The modifier for styling and positioning the article list.
 * @param articles The list of articles to display.
 * @param onClick Callback function to handle click events on articles.
 */
@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
    ) {
        items(count = articles.size) { it ->
            val article = articles[it]
            ArticleCard(article = article, onClick = { onClick(article) })
        }
    }
}

/**
 * Composable function for displaying a list of articles using paging.
 * @param modifier The modifier for styling and positioning the article list.
 * @param articles The lazy loading list of articles to display.
 * @param onClick Callback function to handle click events on articles.
 */
@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlingPagingResult = handlePagingResult(articles = articles)
    if (handlingPagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
            contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
        ) {
            items(count = articles.itemCount) { it ->
                articles[it]?.let {
                    ArticleCard(article = it, onClick = { onClick(it) })
                }
            }
        }
    }
}

/**
 * Function to handle the paging result and display appropriate UI.
 * @param articles The lazy loading list of articles.
 * @return Boolean value indicating whether to display the list or not.
 */
@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        articles.itemCount == 0 -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

/**
 * Composable function for displaying a shimmer effect while content is loading.
 */
@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmerEffect(modifier = Modifier.padding(horizontal = Dimens.MediumPadding1))
        }
    }
}