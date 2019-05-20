package com.example.newsfactory.app

import android.app.Application
import android.content.Context


class NewsApp : Application() {

    companion object {
        lateinit var instance: NewsApp

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }


}