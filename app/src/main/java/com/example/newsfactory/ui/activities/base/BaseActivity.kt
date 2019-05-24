package com.example.newsfactory.ui.activities.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsfactory.R
import com.example.newsfactory.common.showFragment


abstract class BaseActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        setUpUi()

    }

    protected fun showFragment(fragment: Fragment){
        showFragment(R.id.mainActivity, fragment)
    }

    abstract fun getLayoutResourceId(): Int

    abstract fun setUpUi()

}