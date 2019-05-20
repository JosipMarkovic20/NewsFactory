package com.example.newsfactory.networking.interactors

import com.example.newsfactory.model.response.GetNewsResponse
import com.example.newsfactory.networking.NewsApiService
import retrofit2.Callback

class NewsInteractorImpl(private val apiService: NewsApiService) : NewsInteractor {

    override fun getNews(newsResponseCallback: Callback<GetNewsResponse>) {
        apiService.getNews().enqueue(newsResponseCallback)
    }

}