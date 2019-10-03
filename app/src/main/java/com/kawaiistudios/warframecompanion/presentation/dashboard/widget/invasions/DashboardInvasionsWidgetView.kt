package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.invasions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import com.kawaiistudios.warframecompanion.presentation.dashboard.DashboardViewDirections
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.view_dashboard_invasions_widget.*
import javax.inject.Inject

class DashboardInvasionsWidgetView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DashboardInvasionsWidgetViewModel

    private val adapter = DashboardInvasionsWidgetAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_dashboard_invasions_widget, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DashboardInvasionsWidgetViewModel::class.java)
        disposable.add(viewModel.rewards.subscribe(adapter::update, ::showSomeError))

        rvRewards.adapter = adapter

        btnShowAllInvasions.setOnClickListener { navigateToInvasionsList() }
    }

    private fun navigateToInvasionsList() {
        val directions = DashboardViewDirections.actionDashboardViewToInvasionsView()
        findNavController().navigate(directions)
    }

    private fun showSomeError(throwable: Throwable) {
        Toast.makeText(context!!, "Some error has occured", Toast.LENGTH_SHORT).show()
    }

}