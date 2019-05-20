package com.example.newsfactory.networking

import com.example.newsfactory.common.BASE_URL
import com.example.newsfactory.networking.interactors.NewsInteractor
import com.example.newsfactory.networking.interactors.NewsInteractorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendFactory {

    private var retrofit: Retrofit? = null
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private val httpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    private val client: Retrofit? = if (retrofit == null) createRetrofit() else retrofit

    private fun createRetrofit(): Retrofit? {
        retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    private fun getService(): NewsApiService = this.client!!.create(NewsApiService::class.java)

    fun getNewsInteractor(): NewsInteractor = NewsInteractorImpl(getService())
}