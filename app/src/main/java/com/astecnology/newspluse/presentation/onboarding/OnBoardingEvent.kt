package com.astecnology.newspluse.presentation.onboarding

/**
 * Sealed class representing events related to onboarding screens.
 */
sealed class OnBoardingEvent {

    /**
     * Event indicating the need to save the app entry after onboarding.
     */
    object  SaveAppEntry : OnBoardingEvent()

}