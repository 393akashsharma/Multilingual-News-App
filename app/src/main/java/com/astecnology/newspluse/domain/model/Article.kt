package com.astecnology.newspluse.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Parcelable data class representing a news article.
 * @param author The author of the article.
 * @param content The content of the article.
 * @param description The description of the article.
 * @param publishedAt The publish date and time of the article.
 * @param source The source of the article.
 * @param title The title of the article.
 * @param url The URL of the article (primary key).
 * @param urlToImage The URL to the image associated with the article.
 */
@Parcelize
@Entity
data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
) : Parcelable