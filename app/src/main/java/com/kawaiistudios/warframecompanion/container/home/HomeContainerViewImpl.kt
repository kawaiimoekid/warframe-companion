package com.kawaiistudios.warframecompanion.container.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kawaiistudios.warframecompanion.R
import kotlinx.android.synthetic.main.fragment_home_container.*

class HomeContainerViewImpl : Fragment(), IHomeContainerView {

    private val mPresenter: IHomeContainerPresenter by lazy { HomeContainerPresenterImpl(this) }
    private val mAdapter by lazy { HomeContainerPagerAdapter(activity as AppCompatActivity) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_home_container, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = mAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    companion object {
        fun newInstance() = HomeContainerViewImpl()
    }
}
