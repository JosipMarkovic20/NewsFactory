package com.example.newsfactory.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.newsfactory.R
import com.example.newsfactory.app.NewsApp
import com.example.newsfactory.common.EXTRA_NEWS_ID
import com.example.newsfactory.model.News
import com.example.newsfactory.persistance.NewsRoomRepository
import com.example.newsfactory.ui.activities.ContainerActivity
import com.example.newsfactory.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news_details.*



class NewsDetailsFragment : BaseFragment(){

    private var newsID = NO_NEWS
    private val repository = NewsRoomRepository()


    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_news_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(EXTRA_NEWS_ID)?.let { newsID = it }
        displayTask(repository.getNewsBy(newsID))
    }


    private fun displayTask(news: News) {
        Glide.with(NewsApp.getAppContext())
            .load(news.pictureUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(newsDetailsPic)
        newsDetailsTitle.text = news.title
        newsDetailsDescription.text = news.description
    }



    companion object {
        const val NO_NEWS = -1

        fun newInstance(newsId: Int): NewsDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_NEWS_ID, newsId) }
            return NewsDetailsFragment().apply { arguments = bundle }
        }
    }
}