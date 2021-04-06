package com.myproject.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myproject.newsapp.databinding.ItemArticlePreviewBinding
import com.myproject.newsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root)
    // https://dev.to/theimpulson/using-recyclerview-with-viewbinding-in-android-via-kotlin-1hgl

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [Article] has been updated.
     */
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            // normally we would compare ID's here but we get the articles from the API and they don't have
            // an ID by default. We only use ID for ur DB, that's why we compare URLs instead
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    // https://developer.android.com/reference/androidx/recyclerview/widget/AsyncListDiffer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticlePreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.binding.ivArticleImage)
            holder.binding.tvSource.text = article.source.name
            holder.binding.tvTitle.text = article.title
            holder.binding.tvDescription.text = article.description
            holder.binding.tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                    // 'it' refers to the variable checked for null. In this case, onItemCLickListener lambda function
                    // ?.let = check if it is null https://kotlinlang.org/docs/null-safety.html#checking-for-null-in-conditions
                    onItemClickListener?.let { it(article) }
            }
        }
    }

    /**
     * Sets the click listener for the article to display
     */
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}