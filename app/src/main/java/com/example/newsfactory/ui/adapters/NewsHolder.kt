package com.example.newsfactory.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfactory.R
import com.example.newsfactory.app.NewsApp
import com.example.newsfactory.model.News
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.view.*

class NewsHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindData(news: News, onItemSelected: (News) -> Unit) {

        containerView.setOnClickListener { onItemSelected(news) }
        containerView.newsTitle.text = news.title
        Glide.with(NewsApp.getAppContext())
            .load(news.pictureUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(containerView.newsPic)

    }
}