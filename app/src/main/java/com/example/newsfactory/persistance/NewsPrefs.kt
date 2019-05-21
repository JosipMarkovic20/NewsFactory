package com.example.newsfactory.persistance

import android.preference.PreferenceManager
import com.example.newsfactory.app.NewsApp

object NewsPrefs{

    const val TIMESTAMP = "TIMESTAMP"

    private fun sharedPrefs() = PreferenceManager.getDefaultSharedPreferences(NewsApp.getAppContext())


    fun store(key: String, value: Long){

        sharedPrefs().edit().putLong(key,value).apply()

    }

    fun getLong(key: String, defaultValue: Long): Long{

        return sharedPrefs().getLong(key,defaultValue)

    }

}