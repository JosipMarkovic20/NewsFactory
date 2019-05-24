package com.example.newsfactory.ui.activities

import com.example.newsfactory.R
import com.example.newsfactory.common.EXTRA_NEWS_ID
import com.example.newsfactory.common.EXTRA_SCREEN_TYPE
import com.example.newsfactory.ui.activities.base.BaseActivity
import com.example.newsfactory.ui.fragments.PagerFragment

class ContainerActivity: BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {

        val screenType = intent.getStringExtra(EXTRA_SCREEN_TYPE)
        val id = intent.getIntExtra(EXTRA_NEWS_ID, -1)
        val fragment = PagerFragment(id)
        if (screenType.isNotEmpty()) {
            when (screenType) {
                SCREEN_NEWS_DETAILS -> showFragment(fragment)
            }
        } else {
            finish()
        }

    }

    companion object{
        const val SCREEN_NEWS_DETAILS = "news_details"
    }
}