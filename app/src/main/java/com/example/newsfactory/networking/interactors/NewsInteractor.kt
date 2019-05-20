package com.example.newsfactory.networking.interactors

import com.example.newsfactory.model.response.GetNewsResponse
import retrofit2.Callback

interface NewsInteractor {

    fun getNews(newsResponseCallback: Callback<GetNewsResponse>)

}