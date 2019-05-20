package com.example.newsfactory.persistance

import com.example.newsfactory.model.News

interface NewsRepository {

    fun addNews(news: News)

    fun getNews(): List<News>

    fun getNewsBy(id: Int): News

    fun clearAllNews()

}