package com.example.newsfactory.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfactory.R
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
import com.example.newsfactory.common.*
import com.example.newsfactory.persistance.NewsPrefs
import com.example.newsfactory.persistance.NewsRoomRepository


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

    private fun initUi() {
        newsRecyclerView.layoutManager = LinearLayoutManager(context)
        newsRecyclerView.adapter = adapter
        val currentTime = getCurrentTimestamp()
        if((currentTime!!.equals(0)||checkTime(currentTime)||repository.sizeOfDb().equals(0))&&isConnected()){
            progress.visible()
            getAllNews()
        }
        else if (repository.sizeOfDb().equals(0)&&!isConnected()){
            errorDialog()
        }else{
            adapter.setData(repository.getNews())
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        return isConnected
    }

    private fun onItemSelected(news: News){
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_NEWS_DETAILS)
            putExtra(EXTRA_NEWS_ID, news.newsDbId)
        }
        startActivity(detailsIntent)
    }

    private fun getAllNews() {
        progress.visible()
        interactor.getNews(getNewsCallback())
    }

    private fun getNewsCallback(): Callback<GetNewsResponse> = object : Callback<GetNewsResponse> {
        override fun onFailure(call: Call<GetNewsResponse>?, t: Throwable?) {
            progress.gone()
            errorDialog()
        }

        override fun onResponse(call: Call<GetNewsResponse>?, response: Response<GetNewsResponse>) {
            progress.gone()
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

    private fun handleSomethingWentWrong() = errorDialog()

    private fun onNewsReceived(news: MutableList<News>) {
        repository.clearAllNews()
        news.forEach{
            repository.addNews(it)
        }
        adapter.setData(repository.getNews())
        saveTimestamp(System.currentTimeMillis()/1000)
    }

    private fun saveTimestamp(timestamp: Long) {
        NewsPrefs.store(NewsPrefs.TIMESTAMP,timestamp)
    }

    private fun getCurrentTimestamp(): Long? {
        return NewsPrefs.getLong(NewsPrefs.TIMESTAMP,0)
    }

    private fun checkTime(oldTimestamp: Long): Boolean{
        val difference = (System.currentTimeMillis()/1000) - oldTimestamp
        return difference>300
    }

    private fun errorDialog() {
        AlertDialog.Builder(context!!)
            .setTitle(getString(R.string.error))
            .setMessage(getString(R.string.errorMsg))
            .setNeutralButton(getString(R.string.ok),null)
            .show()
    }

    companion object {
        fun newInstance(): Fragment {
            return NewsFragment()
        }
    }
}