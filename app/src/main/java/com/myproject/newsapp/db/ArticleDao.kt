package com.myproject.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.myproject.newsapp.models.Article

@Dao
interface ArticleDao {

    // insert or update an article
    // what happens if article already exists
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // update or insert
    suspend fun upsert(article: Article): Long

    // pass SQL query that selects all articles that this function should return
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    // Delete article
    @Delete
    suspend fun deleteArticle(article: Article)
}