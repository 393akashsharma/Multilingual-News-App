package com.astecnology.newspluse.presentation.onboarding

import androidx.annotation.DrawableRes
import com.astecnology.newspluse.R

/**
 * Data class representing a page in the onboarding screen.
 * @property title The title of the page.
 * @property description The description of the page.
 * @property image The drawable resource ID of the image associated with the page.
 */
data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

/**
 * List of predefined onboarding pages.
 */
val pages = listOf(
    Page(
        title = "Business News Snapshot",
        description = "Get quick insights into market moves, corporate updates, and economic trends with our concise business news roundup. Stay informed, stay ahead.",
        image = R.drawable.onboardingbusiness
    ),
    Page(
        title = "Breaking Tech News Waves",
        description = "Dive into the latest in tech innovation, trends, and breakthroughs. Stay afloat with our succinct updates on gadgets, AI, cybersecurity, and more",
        image = R.drawable.onboardingtechnology
    ),
    Page(
        title = "Catch the Entertainment Buzz",
        description = "Stay tuned to the latest in entertainment, from movie releases to celebrity gossip. Get your dose of pop culture updates in a flash",
        image = R.drawable.onboardingentertainment
    )
)
