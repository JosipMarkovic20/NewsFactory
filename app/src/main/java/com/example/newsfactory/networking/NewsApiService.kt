package com.example.newsfactory.networking

import com.example.newsfactory.model.response.GetNewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {

    @GET("articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398")
    fun getNews(): Call<GetNewsResponse>

}