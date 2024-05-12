package com.astecnology.newspluse.presentation.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.astecnology.newspluse.ui.theme.NewsPluseTheme
import com.astecnology.newspluse.presentation.Dimens.IndicatorSize


/**
 * Composable function for rendering a page indicator.
 *
 * @param modifier Modifier to apply to the page indicator.
 * @param pageSize Total number of pages.
 * @param selectedPage Index of the currently selected page.
 * @param selectedPageColor Color for the selected page indicator.
 * @param unSelectedColor Color for the unselected page indicators.
 */
@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedPageColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = Color.Blue
) {
    // Row to contain individual page indicators
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        // Repeat for each page
        repeat(pageSize) { page ->
            // Box representing each page indicator
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedPageColor else unSelectedColor)
            )
        }
    }
}

/**
 * Composable function for previewing the PageIndicator.
 * It displays a sample PageIndicator with a specific page size and selected page index.
 */
@Composable
@Preview(showBackground = true)
fun PageIndicatorPreview() {
    // Preview the PageIndicator within the NewsAppTheme
    NewsPluseTheme {
        // Display a PageIndicator with a page size of 3 and the second page selected
        PageIndicator(pageSize = 3, selectedPage = 1)
    }
}