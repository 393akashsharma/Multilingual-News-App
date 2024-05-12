package com.astecnology.newspluse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.astecnology.newspluse.domain.model.Article

/**
 * Room Database class for managing news articles stored locally.
 * Defines the database entities and version, and specifies the DAO to access the database.
 */
@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    /**
     * Provides access to the DAO (Data Access Object) for interacting with the database.
     */
    abstract val newsDao : NewsDao
}