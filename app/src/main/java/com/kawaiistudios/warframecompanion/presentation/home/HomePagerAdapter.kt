package com.kawaiistudios.warframecompanion.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class HomePagerAdapter(
        fragmentManager: FragmentManager,
        private val fragments: List<Pair<String, Fragment>>
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = fragments[position].second
    override fun getCount() = fragments.size
    override fun getPageTitle(position: Int) = fragments[position].first

}