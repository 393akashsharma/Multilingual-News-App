package com.astecnology.newspluse.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Parcelable data class representing the source of a news article.
 * @param id The unique identifier of the source.
 * @param name The name of the source.
 */
@Parcelize
data class Source(
    val id: String,
    val name: String
) : Parcelable