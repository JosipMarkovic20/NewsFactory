package com.example.newsfactory.persistance

import com.example.newsfactory.app.NewsApp
import com.example.newsfactory.db.DaoProvider
import com.example.newsfactory.db.NewsDao
import com.example.newsfactory.model.News


class NewsRoomRepository : NewsRepository {


    private var db: DaoProvider = DaoProvider.getInstance(NewsApp.getAppContext())
    private var newsDao: NewsDao = db.newsDao()

    override fun addNews(news: News) {
        newsDao.insertNews(news)
    }

    override fun getNews(): MutableList<News> {
        return newsDao.loadAll()
    }

    override fun getNewsBy(id: Int): News {
        return newsDao.getNews(id)
    }

    override fun clearAllNews() {
        newsDao.deleteAllNews()
    }

}
