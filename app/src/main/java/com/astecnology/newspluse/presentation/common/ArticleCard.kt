package com.astecnology.newspluse.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.astecnology.newspluse.R
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.domain.model.Source
import com.astecnology.newspluse.presentation.Dimens
import com.astecnology.newspluse.util.NewsUtils

/**
 * Composable function for displaying an article card.
 * @param modifier The modifier for styling and positioning the article card.
 * @param article The article to display.
 * @param onClick Callback function to handle click events on the article card.
 */
@Composable
fun ArticleCard(modifier: Modifier = Modifier, article: Article, onClick: () -> Unit) {

    val context = LocalContext.current

    Row(modifier = modifier.clickable { onClick() }) {
        AsyncImage(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))

                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.SmallIconSize)
                )
                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))
                Text(
                    text = NewsUtils.getTimeAgoString(timeString = article.publishedAt),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        }
    }
}

/**
 * Preview function for the ArticleCard composable.
 */
@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ArticleCardPreview() {
    ArticleCard(
        article = Article(
            author = "Joel Khalili",
            content = "For eight years, Craig Wright has claimed to be the elusive Bitcoin creator Satoshi Nakamoto. On Monday, in the swelling heat of a UK courtroom, a trial began that will finally settle the question.\\r\\n… [+3163 chars]",
            description = "A UK High Court will settle a long-running debate over whether Craig Wright really is Satoshi Nakamoto, inventor of Bitcoin. Monday’s opening arguments laid the groundwork for both sides.",
            publishedAt = "2024-02-05T21:07:04Z",
            source = Source(id = "wired", name = "Al Jeera English"),
            title = "The Trial Over Bitcoin’s True Creator Is in Session",
            url = "https://www.wired.com/story/craig-wright-bitcoin-satoshi-nakamoto-trial/",
            urlToImage = "https://media.wired.com/photos/65bd7e2524c06ba3ede91a33/191:100/w_1280,c_limit/Craig-Wright-Trial-Day-1-Business-Yellow-1494808061.jpg"
        )
    ) {
    }
}