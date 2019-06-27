package com.kawaiistudios.warframecompanion.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.presentation.BaseView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.fissures.DashboardFissuresWidgetView
import com.kawaiistudios.warframecompanion.presentation.dashboard.widget.news.DashboardNewsWidgetView
import com.kawaiistudios.warframecompanion.di.Injectable
import kotlinx.android.synthetic.main.view_dashboard.*
import javax.inject.Inject

class DashboardView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DashboardViewModel::class.java)

        if (childFragmentManager.fragments.isEmpty()) {
            childFragmentManager.beginTransaction()
                    .add(layoutWidgetContainer.id, DashboardNewsWidgetView(), "NewsWidget")
                    .add(layoutWidgetContainer.id, DashboardFissuresWidgetView(), "FissuresWidget")
                    .commit()
        }
    }

}