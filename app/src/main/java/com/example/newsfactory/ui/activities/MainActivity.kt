package com.example.newsfactory.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsfactory.R
import com.example.newsfactory.common.showFragment
import com.example.newsfactory.ui.activities.base.BaseActivity
import com.example.newsfactory.ui.fragments.NewsFragment

class MainActivity : BaseActivity() {


    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        showFragment(NewsFragment.newInstance())
    }


}
