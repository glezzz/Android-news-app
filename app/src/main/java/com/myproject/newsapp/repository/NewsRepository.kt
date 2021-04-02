package com.myproject.newsapp.repository

import com.myproject.newsapp.api.RetrofitInstance
import com.myproject.newsapp.db.ArticleDatabase

/**
 * Get data from DB and remote data source (Retrofit & API)
 */
class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    /**
     * Search functionality
     */
    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
}