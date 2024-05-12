package com.astecnology.newspluse.di

import android.app.Application
import androidx.room.Room
import com.astecnology.newspluse.data.local.NewsDao
import com.astecnology.newspluse.data.local.NewsDatabase
import com.astecnology.newspluse.data.local.NewsTypeConverter
import com.astecnology.newspluse.data.manager.LocalUserManagerImpl
import com.astecnology.newspluse.data.remote.NewsApi
import com.astecnology.newspluse.data.remote.repository.NewsRepositoryImpl
import com.astecnology.newspluse.domain.manager.LocalUserManager
import com.astecnology.newspluse.domain.repository.NewsRepository
import com.astecnology.newspluse.domain.usecases.appEntry.AppEntryUseCases
import com.astecnology.newspluse.domain.usecases.appEntry.ReadAppEntry
import com.astecnology.newspluse.domain.usecases.appEntry.SaveAppEntry
import com.astecnology.newspluse.domain.usecases.news.DeleteArticle
import com.astecnology.newspluse.domain.usecases.news.GetNews
import com.astecnology.newspluse.domain.usecases.news.NewsUseCases
import com.astecnology.newspluse.domain.usecases.news.SearchNews
import com.astecnology.newspluse.domain.usecases.news.SelectArticle
import com.astecnology.newspluse.domain.usecases.news.SelectArticles
import com.astecnology.newspluse.domain.usecases.news.UpsertArticle
import com.astecnology.newspluse.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing dependencies for the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of LocalUserManager.
     */
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    /**
     * Provides a singleton instance of AppEntryUseCases.
     */
    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    /**
     * Provides a singleton instance of NewsApi.
     */
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)
    }

    /**
     * Provides a singleton instance of NewsRepository.
     */
    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository =
        NewsRepositoryImpl(newsApi, newsDao = newsDao)

    /**
     * Provides a singleton instance of NewsUseCases.
     */
    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository, newsDao: NewsDao): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    /**
     * Provides a singleton instance of NewsDatabase.
     */
    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constants.NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter()).fallbackToDestructiveMigration().build()
    }

    /**
     * Provides a singleton instance of NewsDao.
     */
    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao
}