package com.astecnology.newspluse.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.astecnology.newspluse.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for accessing news articles from the local database.
 * Defines methods for inserting, deleting, and querying articles.
 */
@Dao
interface NewsDao {

    /**
     * Inserts or replaces an article in the database.
     * If the article already exists, it will be replaced.
     * @param article The article to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    /**
     * Deletes the specified article from the database.
     * @param article The article to be deleted.
     */
    @Delete
    suspend fun delete(article: Article)

    /**
     * Retrieves all articles from the database.
     * @return A Flow of lists of articles stored in the database.
     */
    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>

    /**
     * Retrieves an article from the database based on its URL.
     * @param url The URL of the article to be retrieved.
     * @return The article corresponding to the given URL, if it exists; otherwise, null.
     */
    @Query("SELECT * FROM Article WHERE url= :url")
    suspend fun getArticle(url: String): Article?
}