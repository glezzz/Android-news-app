package com.myproject.newsapp.ui

import androidx.lifecycle.ViewModel
import com.myproject.newsapp.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
}