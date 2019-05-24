package com.example.newsfactory.ui.activities

import com.example.newsfactory.R
import com.example.newsfactory.ui.activities.base.BaseActivity
import com.example.newsfactory.ui.fragments.NewsFragment

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        showFragment(NewsFragment.newInstance())
    }

}
