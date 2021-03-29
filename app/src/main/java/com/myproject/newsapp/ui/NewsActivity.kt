package com.myproject.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.myproject.newsapp.R
import com.myproject.newsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        // Connect bottom navigation view with navigation components
        binding.bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
    }
}