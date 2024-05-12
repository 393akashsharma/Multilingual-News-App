package com.astecnology.newspluse.domain.usecases.appEntry

import com.astecnology.newspluse.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

/**
 * Use case class for reading the app entry preference.
 * @param localUserManager The LocalUserManager instance for accessing user preferences.
 */
class ReadAppEntry(private val localUserManager: LocalUserManager) {

    /**
     * Invokes the use case to read the app entry preference.
     * @return A Flow emitting the value of the app entry preference.
     */
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}