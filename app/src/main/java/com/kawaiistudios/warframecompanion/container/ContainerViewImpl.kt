package com.kawaiistudios.warframecompanion.container

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.container.home.HomeContainerViewImpl
import com.kawaiistudios.warframecompanion.container.market.MarketFragment
import com.kawaiistudios.warframecompanion.container.home.news.NewsViewImpl
import kotlinx.android.synthetic.main.activity_container.*

class ContainerViewImpl : AppCompatActivity(), IContainerView {

    private val mPresenter: IContainerPresenter by lazy { ContainerPresenterImpl() }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mPresenter.openFragment(supportFragmentManager, fragmentContainer, HomeContainerViewImpl.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_market -> {
                mPresenter.openFragment(supportFragmentManager, fragmentContainer, MarketFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_items -> {
                mPresenter.openFragment(supportFragmentManager, fragmentContainer, MarketFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_relics -> {
                mPresenter.openFragment(supportFragmentManager, fragmentContainer, MarketFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mPresenter.openFragment(supportFragmentManager, fragmentContainer, HomeContainerViewImpl.newInstance())
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}