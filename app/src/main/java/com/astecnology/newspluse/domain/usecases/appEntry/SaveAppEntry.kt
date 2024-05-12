package com.astecnology.newspluse.domain.usecases.appEntry

import com.astecnology.newspluse.domain.manager.LocalUserManager

/**
 * Use case class for saving the app entry preference.
 * @param localUserManager The LocalUserManager instance for accessing user preferences.
 */
class SaveAppEntry(private val localUserManager: LocalUserManager) {

    /**
     * Invokes the use case to save the app entry preference.
     */
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}