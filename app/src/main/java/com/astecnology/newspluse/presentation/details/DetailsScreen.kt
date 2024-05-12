package com.astecnology.newspluse.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.astecnology.newspluse.R
import com.astecnology.newspluse.ui.theme.NewsPluseTheme
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.domain.model.Source
import com.astecnology.newspluse.presentation.Dimens
import com.astecnology.newspluse.presentation.details.component.DetailsTopBar


@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "type/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article))
            }, onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1,
                top = Dimens.MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(), contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = colorResource(id = R.color.text_title)
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

/**
 * Preview function for the DetailsScreen composable.
 */
@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview() {
    NewsPluseTheme {
        DetailsScreen(
            article = Article(
                author = "Joel Khalili",
                content = "For eight years, Craig Wright has claimed to be the elusive Bitcoin creator Satoshi Nakamoto. On Monday, in the swelling heat of a UK courtroom, a trial began that will finally settle the question.\\r\\n… [+3163 chars]",
                description = "A UK High Court will settle a long-running debate over whether Craig Wright really is Satoshi Nakamoto, inventor of Bitcoin. Monday’s opening arguments laid the groundwork for both sides.",
                publishedAt = "2024-02-05T21:07:04Z",
                source = Source(id = "wired", name = "Wired"),
                title = "The Trial Over Bitcoin’s True Creator Is in Session",
                url = "https://www.wired.com/story/craig-wright-bitcoin-satoshi-nakamoto-trial/",
                urlToImage = "https://media.wired.com/photos/65bd7e2524c06ba3ede91a33/191:100/w_1280,c_limit/Craig-Wright-Trial-Day-1-Business-Yellow-1494808061.jpg"
            ), event = {}, navigateUp = {}
        )
    }
}