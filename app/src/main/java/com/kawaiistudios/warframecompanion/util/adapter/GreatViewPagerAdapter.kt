package com.kawaiistudios.warframecompanion.util.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class GreatViewPagerAdapter(manager: FragmentManager)
    : FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    override fun getPageTitle(position: Int) = titles[position]
    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }

}