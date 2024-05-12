package com.astecnology.newspluse.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.astecnology.newspluse.R
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.presentation.Dimens
import com.astecnology.newspluse.presentation.common.ArticleList
import com.astecnology.newspluse.presentation.common.SearchBar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    // Extract titles of the first 10 articles for display
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.MediumPadding1)
            .statusBarsPadding()
    ) {
        // Display app logo
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.MediumPadding1),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = Dimens.MediumPadding1)
            )
            Text(text = "NewsPluse",
                style = MaterialTheme.typography.labelLarge.copy(color = Color.Blue, fontSize = 24.sp)
            )

        }

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        SearchBar(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch()
            },
            onSearch = {})
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        // Display titles of the first 10 articles
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        // Display list of articles
        Card( modifier = Modifier
            .background(
            MaterialTheme.colorScheme.background,
            MaterialTheme.shapes.extraSmall
        )
        ) {
            ArticleList(
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
                articles = articles,
                onClick = {
                    navigateToDetails(it)
                })
        }
    }
}