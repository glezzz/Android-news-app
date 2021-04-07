package com.myproject.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.newsapp.models.Article
import com.myproject.newsapp.models.NewsResponse
import com.myproject.newsapp.repository.NewsRepository
import com.myproject.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    // to save the current response for pagination
    var breakingNewsResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null

    init {
        getBreakingNews("us")
    }

    /**
     * Execute api call
     */
    // viewModelScope makes sure the coroutine stays alive only as long as the viewModel is alive
    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        // emit the loading state to LiveData
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    /**
     * Search for news
     */
    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                // after successful, increase/create page numbering
                breakingNewsPage++
                // first response
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = resultResponse

                // if it's not null (not the first page) add them to the list of articles
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    // add the new articles to the list
                    oldArticles?.addAll(newArticles)
                }
                            // if breakingNewsResponse is null (first response), return resultResponse instead
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                // after successful, increase/create page numbering
                searchNewsPage++
                // first response
                if (searchNewsResponse == null) {
                    searchNewsResponse = resultResponse

                    // if it's not null (not the first page) add them to the list of articles
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    // add the new articles to the list
                    oldArticles?.addAll(newArticles)
                }
                // if breakingNewsResponse is null (first response), return resultResponse instead
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    /**
     * Save article in saved news
     */
    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    /**
     * Delete article from saved news
     */
    fun deleteSavedArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}