package com.astecnology.newspluse.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.astecnology.newspluse.presentation.news_navigator.NewsNavigator
import com.astecnology.newspluse.presentation.onboarding.OnBoardingViewModel
import com.astecnology.newspluse.presentation.onboarding.component.OnBoardingScreen

/**
 * Composable function defining the navigation graph of the application.
 * Manages navigation between different screens using Jetpack Navigation.
 *
 * @param startDestination The starting destination of the navigation graph.
 */
@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        // Navigation for app start, such as onboarding screens
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }
        // Navigation for news-related screens
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route){
                NewsNavigator()
            }
        }
    }
}