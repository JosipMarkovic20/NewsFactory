package com.example.newsfactory.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.newsfactory.model.News
import com.example.newsfactory.persistance.NewsRoomRepository
import com.example.newsfactory.ui.activities.ContainerActivity
import com.example.newsfactory.ui.fragments.NewsDetailsFragment

class NewsPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    private val repository = NewsRoomRepository()
    private val newsList: List<News> = repository.getNews()

    override fun getItem(position: Int): Fragment {
        val newsObject = newsList.get(position).newsDbId
        val fragment = NewsDetailsFragment.newInstance(newsObject!!)
        return fragment
    }


    override fun getCount() = newsList.size
}