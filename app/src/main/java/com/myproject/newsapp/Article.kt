package com.myproject.newsapp

import androidx.room.Entity
import androidx.room.PrimaryKey

// To save an article in DB. This annotation tells Android Studio that article is a table in our DB
@Entity(
    tableName = "articles"
)
data class Article(
    // nullable because not every article will have an ID. The ones that aren't saved into the DB (only displayed in the app) don't need an ID.
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)