package com.astecnology.newspluse.domain.usecases.appEntry

/**
 * Data class representing the use cases related to the app entry preference.
 * @param readAppEntry The use case for reading the app entry preference.
 * @param saveAppEntry The use case for saving the app entry preference.
 */
data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)