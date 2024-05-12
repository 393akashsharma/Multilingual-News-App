package com.astecnology.newspluse.presentation.common

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astecnology.newspluse.R
import com.astecnology.newspluse.ui.theme.NewsPluseTheme
import com.astecnology.newspluse.presentation.Dimens


/**
 * Modifier function for adding a shimmer effect to the composable.
 * The shimmer effect simulates a loading animation by gradually changing the opacity of the background color.
 * @return The modified modifier with the shimmer effect.
 */
fun Modifier.shimmerEffect() = this.then(
    composed {
        val transition = rememberInfiniteTransition(label = "")
        val alpha = transition.animateFloat(
            initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Restart
            ), label = ""
        ).value
        background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
    }
)

/**
 * Composable function for displaying a shimmer effect on an article card.
 * @param modifier The modifier for styling the shimmer effect.
 */
@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {

        Box(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.ArticleCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = Dimens.MediumPadding1)
                    .shimmerEffect()
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(15.dp)
                        .padding(horizontal = Dimens.MediumPadding1)
                        .shimmerEffect()
                )
            }
        }
    }
}

/**
 * Preview function for the ArticleCardShimmerEffect composable.
 */
@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ArticleCardShimmerEffectPreview() {
    NewsPluseTheme {
        ArticleCardShimmerEffect()
    }
}