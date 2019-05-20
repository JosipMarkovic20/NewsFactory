package com.example.newsfactory.ui.fragments

import android.content.Intent
import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfactory.R
import com.example.newsfactory.common.EXTRA_NEWS_ID
import com.example.newsfactory.common.EXTRA_SCREEN_TYPE
import com.example.newsfactory.common.RESPONSE_OK
import com.example.newsfactory.common.displayToast
import com.example.newsfactory.model.News
import com.example.newsfactory.model.response.GetNewsResponse
import com.example.newsfactory.networking.BackendFactory
import com.example.newsfactory.ui.activities.ContainerActivity
import com.example.newsfactory.ui.adapters.NewsAdapter
import com.example.newsfactory.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONException
import android.widget.Toast
import com.example.newsfactory.persistance.NewsRoomRepository
import com.example.newsfactory.ui.activities.MainActivity
import org.json.JSONObject
import org.json.JSONArray



class NewsFragment : BaseFragment() {


    private val adapter by lazy { NewsAdapter {onItemSelected(it)} }
    private val repository = NewsRoomRepository()
    private val interactor = BackendFactory.getNewsInteractor()

    override fun getLayoutResourceId() = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initUi()
    }

    override fun onDestroy() {
        repository.clearAllNews()
        super.onDestroy()
    }


    private fun initUi() {
        newsRecyclerView.layoutManager = LinearLayoutManager(context)
        newsRecyclerView.adapter = adapter
        getAllNews()
    }

    private fun onItemSelected(news: News){
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_NEWS_DETAILS)
            putExtra(EXTRA_NEWS_ID, news.newsDbId)
        }
        startActivity(detailsIntent)
    }

    private fun getAllNews() {
        interactor.getNews(getNewsCallback())
    }

    private fun getNewsCallback(): Callback<GetNewsResponse> = object : Callback<GetNewsResponse> {
        override fun onFailure(call: Call<GetNewsResponse>?, t: Throwable?) {
            //TODO : handle default error
        }

        override fun onResponse(call: Call<GetNewsResponse>?, response: Response<GetNewsResponse>) {

            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkResponse(response: Response<GetNewsResponse>) {
        response.body()?.articles?.run {
            onNewsReceived(this)
        }
    }


    private fun handleSomethingWentWrong() = this.activity?.displayToast("Something went wrong!")


    private fun onNewsReceived(news: MutableList<News>) {
        news.forEach{
            repository.addNews(it)
        }
        adapter.setData(repository.getNews())
    }


    companion object {
        fun newInstance(): Fragment {
            return NewsFragment()
        }
    }
}