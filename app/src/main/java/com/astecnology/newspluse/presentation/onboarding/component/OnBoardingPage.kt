package com.astecnology.newspluse.presentation.onboarding.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.astecnology.newspluse.R
import com.astecnology.newspluse.presentation.Dimens
import com.astecnology.newspluse.presentation.onboarding.Page
import com.astecnology.newspluse.presentation.onboarding.pages
import com.astecnology.newspluse.ui.theme.NewsPluseTheme


/**
 * Composable function for rendering an onboarding page.
 *
 * @param modifier Modifier to be applied to the root element.
 * @param page Page object containing the data to be displayed.
 */
@Composable
fun OnBoardingPage(modifier: Modifier = Modifier, page: Page) {
    Column(modifier = modifier) {
        // Display the image with specified content scale
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            contentScale = ContentScale.Crop
        )
        // Add space between the image and text elements
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        // Display the title text with specified style and color
        Text(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding2),
            text = page.title,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            color = colorResource(id = R.color.display_small)
        )
        // Display the description text with specified style and color
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

/**
 * Preview function for OnBoardingPage composable in light theme.
 */
@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun OnBoardingPagePreview() {
    NewsPluseTheme {
        OnBoardingPage(page = pages[0])
    }
}