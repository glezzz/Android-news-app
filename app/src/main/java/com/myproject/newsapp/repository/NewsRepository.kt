package com.myproject.newsapp.repository

import com.myproject.newsapp.api.RetrofitInstance
import com.myproject.newsapp.db.ArticleDatabase
import com.myproject.newsapp.models.Article

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

    /**
     * Add to saved articles
     */
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    /**
     * Displays saved news
     */
    // not a suspend fun because it returns liveData
    fun getSavedNews() = db.getArticleDao().getAllArticles()

    /**
     * Delete article from saved news
     */
    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}