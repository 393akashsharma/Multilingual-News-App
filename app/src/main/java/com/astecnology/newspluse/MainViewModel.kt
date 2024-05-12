package com.astecnology.newspluse

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astecnology.newspluse.domain.usecases.appEntry.AppEntryUseCases
import com.astecnology.newspluse.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * MainViewModel controls the initialization flow of the app. It is annotated with @HiltViewModel
 * for integration with Hilt dependency injection.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases) :
    ViewModel() {

    // Mutable state variable to control splash screen visibility
    var splashCondition by mutableStateOf(true)
        private set

    // Mutable state variable to determine the start destination of the app
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    /**
     * Initialization block to set up the MainViewModel.
     */
    init {
        // Launch a coroutine to read the app entry state
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            // Determine the start destination based on the app entry state
            startDestination = if (shouldStartFromHomeScreen) {
                Route.NewsNavigation.route
            } else {
                Route.AppStartNavigation.route
            }
            // Delay to allow the splash screen to be displayed for a brief moment
            delay(300)
            // Update splashCondition to hide the splash screen
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}