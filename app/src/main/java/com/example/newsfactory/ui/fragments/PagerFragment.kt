package com.example.newsfactory.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.newsfactory.R
import com.example.newsfactory.model.News
import com.example.newsfactory.persistance.NewsRoomRepository
import com.example.newsfactory.ui.activities.ContainerActivity
import com.example.newsfactory.ui.adapters.NewsPagerAdapter

class PagerFragment(val newsId: Int) : Fragment() {

    val repository = NewsRoomRepository()
    val newsList: List<News> = repository.getNews()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result = inflater.inflate(R.layout.news_pager, container, false)
        val pager = result.findViewById(R.id.viewPager) as ViewPager

        pager.adapter = buildAdapter()

        val position = checkId()

        pager.setCurrentItem(position)

        return result
    }

    private fun checkId(): Int {
        for (x in 0 until newsList.size) {
            if (newsId == newsList[x].newsDbId) return x
        }
        return 0
    }

    private fun buildAdapter(): PagerAdapter {
        return NewsPagerAdapter(childFragmentManager)
    }

}