package com.astecnology.newspluse.presentation.onboarding.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.astecnology.newspluse.presentation.Dimens
import com.astecnology.newspluse.presentation.common.NewsButton
import com.astecnology.newspluse.presentation.common.NewsTextButton
import com.astecnology.newspluse.presentation.onboarding.OnBoardingEvent
import com.astecnology.newspluse.presentation.onboarding.pages
import kotlinx.coroutines.launch

/**
 * Composable function for rendering the onboarding screen.
 * @param event Lambda function to handle events triggered from the onboarding screen.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(event: (OnBoardingEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Remember the state of the pager
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        // Derive button state based on current page
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        // Horizontal pager to display onboarding pages
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        // Row containing page indicator and navigation buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Page indicator
            PageIndicator(
                modifier = Modifier.width(Dimens.PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )
            // Navigation buttons
            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                // Back button
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(text = buttonState.value[0], onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    })
                }
                // Next/Get Started button
                NewsButton(text = buttonState.value[1], onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            event(OnBoardingEvent.SaveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                })
            }
        }
        // Spacer at the bottom
        Spacer(modifier = Modifier.weight(0.5f))
    }
}