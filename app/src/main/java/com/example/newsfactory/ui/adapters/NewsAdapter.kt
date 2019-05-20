package com.example.newsfactory.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfactory.R
import com.example.newsfactory.model.News

class NewsAdapter(private val onItemSelected: (News) -> Unit) : RecyclerView.Adapter<NewsHolder>() {

    private val data: MutableList<News> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsHolder(v)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bindData(data[position], onItemSelected)
    }

    fun setData(data: MutableList<News>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

}