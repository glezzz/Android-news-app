package com.myproject.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.myproject.newsapp.R
import com.myproject.newsapp.databinding.ActivityNewsBinding
import com.myproject.newsapp.db.ArticleDatabase
import com.myproject.newsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        // Connect bottom navigation view with navigation components
        binding.bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
    }
}