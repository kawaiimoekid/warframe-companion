package com.kawaiistudios.warframecompanion.activity.container

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.fragment.drops.DropsFragment
import com.kawaiistudios.warframecompanion.fragment.news.NewsFragment
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity(), IContainerActivity {

    private lateinit var mPresenter: IContainerPresenter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mPresenter.openFragment(supportFragmentManager, fragmentContainer, NewsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mPresenter.openFragment(supportFragmentManager, fragmentContainer, DropsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.popBackStack()
                setToolbarTitle("test3")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mPresenter = ContainerPresenterImpl()
    }

    override fun setToolbarTitle(title: String) {
        toolbar?.title = title
    }
}
