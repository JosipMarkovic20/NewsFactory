package com.example.newsfactory.networking

import com.example.newsfactory.model.response.GetNewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {

    @GET("articles?source=bbc-news&sortBy=top")
    fun getNews(): Call<GetNewsResponse>

}