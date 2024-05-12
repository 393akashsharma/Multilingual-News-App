package com.astecnology.newspluse.domain.manager

import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing user preferences locally.
 */
interface LocalUserManager {

    /**
     * Saves the app entry preference.
     */
    suspend fun saveAppEntry()

    /**
     * Reads the app entry preference.
     * @return A Flow emitting the value of the app entry preference.
     */
    fun readAppEntry() : Flow<Boolean>
}