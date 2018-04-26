package com.kawaiistudios.warframecompanion.container.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.container.home.news.NewsViewImpl

class HomeContainerPagerAdapter(
        private val mActivity: AppCompatActivity
) : FragmentStatePagerAdapter(mActivity.supportFragmentManager) {

    override fun getItem(position: Int): Fragment {
        return NewsViewImpl.newInstance()
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mActivity.getString(R.string.view_home_container_nav_item_news)
            1 -> mActivity.getString(R.string.view_home_container_nav_item_alerts)
            2 -> mActivity.getString(R.string.view_home_container_nav_item_sorties)
            else -> null
        }
    }
}