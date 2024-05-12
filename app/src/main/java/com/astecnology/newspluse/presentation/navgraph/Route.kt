package com.astecnology.newspluse.presentation.navgraph


sealed class Route(val route: String) {

    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")

    object InshortScreen : Route(route = "inshortScreen")

    object BookmarkScreen : Route(route = "bookmarkScreen")

    object LoginPage : Route(route = "loginpage")

    object DetailsScreen : Route(route = "detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object NewsNavigation : Route(route = "newsNavigation")

    object NewsNavigatorScreen : Route(route = "newsNavigatorScreen")
}