package com.astecnology.newspluse.presentation.news_navigator

import androidx.annotation.DrawableRes

/**
 * Data class representing a bottom navigation item in the news application.
 *
 * @param icon The resource ID of the icon to be displayed for the bottom navigation item.
 * @param text The text label for the bottom navigation item.
 */
data class BottomNavigationItem(@DrawableRes val icon : Int, val text : String)