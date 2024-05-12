package com.astecnology.newspluse.presentation.news_navigator

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.astecnology.newspluse.R
import com.astecnology.newspluse.domain.model.Article
import com.astecnology.newspluse.presentation.Inshort.Inshort
import com.astecnology.newspluse.presentation.Login.LoginPage
import com.astecnology.newspluse.presentation.bookmark.BookmarkScreen
import com.astecnology.newspluse.presentation.bookmark.BookmarkViewModel
import com.astecnology.newspluse.presentation.details.DetailsEvent
import com.astecnology.newspluse.presentation.details.DetailsScreen
import com.astecnology.newspluse.presentation.details.DetailsViewModel
import com.astecnology.newspluse.presentation.home.HomeScreen
import com.astecnology.newspluse.presentation.home.HomeViewModel
import com.astecnology.newspluse.presentation.navgraph.Route
import com.astecnology.newspluse.presentation.news_navigator.components.NewsBottomNavigation
import com.astecnology.newspluse.presentation.search.SearchScreen
import com.astecnology.newspluse.presentation.search.SearchViewModel

/**
 * Composable function for the main navigation flow of the news application.
 * Handles the bottom navigation, switching between different screens.
 *
 * @param startDestination The starting destination of the navigation flow.
 */
@Composable
fun NewsNavigator() {
    // Define the bottom navigation items
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.inshort, text = "Inshort"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
            BottomNavigationItem(icon = R.drawable.login , text="Login")

        )
    }
    // Initialize NavController to manage navigation
    val navController = rememberNavController()
    // Retrieve the current back stack state
    val backStackState = navController.currentBackStackEntryAsState().value
    // Determine the selected item index based on the current destination route
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        Route.LoginPage.route -> 3
        else -> 0
    }

    // Determine if the bottom navigation bar should be visible
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.InshortScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route ||
                backStackState?.destination?.route == Route.LoginPage.route
    }

    // Scaffold with bottom navigation
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.InshortScreen.route
                        )

                        3 -> navigateToTab(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )

                        4 -> navigateToTab(
                            navController = navController,
                            route = Route.LoginPage.route
                        )
                    }
                }
            )
        }
    }) {
        // Calculate bottom padding for the content area
        val bottomPadding = it.calculateBottomPadding()
        // Navigation graph setup
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            // Define composable destinations
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                // Retrieve ViewModel for the HomeScreen
                val viewModel: HomeViewModel = hiltViewModel()
                // Collect news articles
                val articles = viewModel.news.collectAsLazyPagingItems()
                // Render HomeScreen
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                // Retrieve ViewModel for the SearchScreen
                val viewModel: SearchViewModel = hiltViewModel()
                // Retrieve state
                val state = viewModel.state.value
                // Save state when back button is pressed
                OnBackClickStateSaver(navController = navController)
                // Render SearchScreen
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {
                // Retrieve ViewModel for the DetailsScreen
                val viewModel: DetailsViewModel = hiltViewModel()
                // Show side effect if available
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEvent)
                }
                // Retrieve article from saved state handle
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        // Render DetailsScreen
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                        )
                    }

            }
            composable(route = Route.BookmarkScreen.route) {
                // Retrieve ViewModel for the BookmarkScreen
                val viewModel: BookmarkViewModel = hiltViewModel()
                // Retrieve state
                val state = viewModel.state.value
                // Save state when back button is pressed
                OnBackClickStateSaver(navController = navController)
                // Render BookmarkScreen
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }


            composable(route = Route.LoginPage.route) {
                // Retrieve ViewModel for the BookmarkScreen
                val viewModel: BookmarkViewModel = hiltViewModel()
                // Retrieve state
                val state = viewModel.state.value
                // Save state when back button is pressed
                OnBackClickStateSaver(navController = navController)
                // Render BookmarkScreen
                LoginPage()
            }


            composable(route = Route.InshortScreen.route) {
                // Retrieve ViewModel for the BookmarkScreen
                val viewModel: BookmarkViewModel = hiltViewModel()
                // Retrieve state
                val state = viewModel.state.value
                // Save state when back button is pressed
                OnBackClickStateSaver(navController = navController)
                // Render BookmarkScreen
                Inshort()
            }
        }
    }
}

/**
 * Composable function to save the navigation state when the back button is pressed.
 * If the back button is pressed, it navigates to the HomeScreen.
 *
 * @param navController NavController instance for managing navigation.
 */
@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        // Navigate to the HomeScreen when the back button is pressed
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

/**
 * Navigates to the specified destination route using the NavController.
 * This function also ensures the navigation stack is properly managed.
 *
 * @param navController NavController instance for managing navigation.
 * @param route Destination route to navigate to.
 */
private fun navigateToTab(navController: NavController, route: String) {
    // Pop up to the start destination route
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        // Ensure a single instance of the destination is at the top of the stack
        launchSingleTop = true
        // Restore the state when navigating
        restoreState = true
    }
}

/**
 * Navigates to the DetailsScreen with the specified article using the NavController.
 *
 * @param navController NavController instance for managing navigation.
 * @param article The article to be passed to the DetailsScreen.
 */
private fun navigateToDetails(navController: NavController, article: Article) {
    // Set the article as a parameter using SavedStateHandle
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    // Navigate to the DetailsScreen route
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}