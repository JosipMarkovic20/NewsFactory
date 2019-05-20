package com.example.newsfactory.db

import androidx.room.*
import com.example.newsfactory.model.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(news: News): Long

    @Query("SELECT * FROM NEWS" )
    fun loadAll(): MutableList<News>

    @Query("SELECT * FROM NEWS WHERE newsDbId = :newsId" )
    fun getNews(newsId: Int): News

    @Query("DELETE FROM NEWS" )
    fun deleteAllNews()

}