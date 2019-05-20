package com.example.newsfactory.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    var newsDbId: Int? = null,
    @SerializedName("author") val author: String,
    @SerializedName("url") val url: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("urlToImage") val pictureUrl: String)