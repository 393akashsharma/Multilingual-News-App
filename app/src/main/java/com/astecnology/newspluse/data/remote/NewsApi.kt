package com.astecnology.newspluse.data.remote

import com.astecnology.newspluse.data.remote.dto.NewsResponse
import com.astecnology.newspluse.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for defining methods to interact with the News API.
 */
interface NewsApi {

    /**
     * Retrieves news articles from the API based on provided sources and page number.
     * @param page The page number of the results to retrieve.
     * @param sources The comma-separated list of news sources to retrieve articles from.
     * @param apiKey The API key used for authentication (default value is provided from Constants.API_KEY).
     * @return A NewsResponse containing the retrieved articles.
     */
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse

    /**
     * Searches for news articles from the API based on the provided search query, sources, and page number.
     * @param searchQuery The search query used to search for news articles.
     * @param page The page number of the results to retrieve.
     * @param sources The comma-separated list of news sources to search for articles from.
     * @param apiKey The API key used for authentication (default value is provided from Constants.API_KEY).
     * @return A NewsResponse containing the searched articles.
     */
    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ) : NewsResponse
}