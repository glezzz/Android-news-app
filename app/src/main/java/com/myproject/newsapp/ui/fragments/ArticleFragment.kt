package com.myproject.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.myproject.newsapp.R
import com.myproject.newsapp.databinding.FragmentArticleBinding
import com.myproject.newsapp.ui.NewsActivity
import com.myproject.newsapp.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    private var binding: FragmentArticleBinding? = null

    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentArticleBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        // get current article
        val article = args.article
        binding?.webView?.apply {
            // page loads inside this webview and not in the standard browser
            webViewClient = WebViewClient()
            // load URL by passing the article URL
            loadUrl(article.url)
        }
    }
}