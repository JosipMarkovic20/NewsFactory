package com.example.newsfactory.model.response

import com.example.newsfactory.model.News

data class GetNewsResponse(
    val articles: MutableList<News>? = null)
