package com.myproject.newsapp.api

import com.myproject.newsapp.NewsResponse
import com.myproject.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    // Get the current top headlines
    @GET("v2/top-headlines")
    // execute function asynchronously using coroutines
    suspend fun getBreakingNews(
        // parameters are part of the request, that's why they're annotated with @Query
        @Query("country")
        countryCode: String = "us",
        // with this parameter we only get 20 articles at once and allows us to paginate
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    // Search for news
    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}