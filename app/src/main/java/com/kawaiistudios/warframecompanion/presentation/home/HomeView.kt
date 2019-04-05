package com.kawaiistudios.warframecompanion.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.presentation.fissures.FissuresView
import com.kawaiistudios.warframecompanion.presentation.news.NewsView
import com.kawaiistudios.warframecompanion.presentation.sortie.SortieView
import com.kawaiistudios.warframecompanion.util.injection.Injectable
import kotlinx.android.synthetic.main.view_home.*
import javax.inject.Inject

class HomeView : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HomeViewModel::class.java)

        viewPager.adapter = HomePagerAdapter(
                childFragmentManager,
                listOf(
                        Pair(getString(R.string.view_home_tab_news), NewsView()),
                        Pair(getString(R.string.view_home_tab_fissures), FissuresView()),
                        Pair(getString(R.string.view_home_tab_sortie), SortieView())
                )
        )
        tabLayout.setupWithViewPager(viewPager)
    }

}