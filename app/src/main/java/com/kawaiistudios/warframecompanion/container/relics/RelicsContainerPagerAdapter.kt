package com.kawaiistudios.warframecompanion.container.relics

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.container.relics.list.RelicsListViewImpl

class RelicsContainerPagerAdapter(
        private val mFragment: Fragment
) : FragmentStatePagerAdapter(mFragment.childFragmentManager) {

    override fun getItem(position: Int): Fragment {
        return RelicsListViewImpl.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mFragment.getString(R.string.view_relics_container_nav_item_lith)
            1 -> mFragment.getString(R.string.view_relics_container_nav_item_meso)
            2 -> mFragment.getString(R.string.view_relics_container_nav_item_neo)
            3 -> mFragment.getString(R.string.view_relics_container_nav_item_axi)
            else -> null
        }
    }
}