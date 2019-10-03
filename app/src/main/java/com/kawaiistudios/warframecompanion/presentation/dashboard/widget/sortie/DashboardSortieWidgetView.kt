package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.sortie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import com.kawaiistudios.warframecompanion.presentation.dashboard.DashboardViewDirections
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.view_dashboard_sortie_widget.*
import javax.inject.Inject

class DashboardSortieWidgetView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DashboardSortieWidgetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_dashboard_sortie_widget, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DashboardSortieWidgetViewModel::class.java)
        disposable.addAll(
                viewModel.sortie.subscribe(::bind, ::showSomeError),
                viewModel.timeLeft.subscribe { txtTimeLeft.text = it }
        )

        btnShowDetails.setOnClickListener { navigateToSortieDetails() }
    }

    private fun bind(sortie: DashboardSortieWidgetModel) {
        txtFaction.text = sortie.factionName
        imgFaction.setImageResource(sortie.factionIcon)

        txtMissionType1.text = sortie.missionType1
        txtMissionModifier1.text = sortie.missionModifier1

        txtMissionType2.text = sortie.missionType2
        txtMissionModifier2.text = sortie.missionModifier2

        txtMissionType3.text = sortie.missionType3
        txtMissionModifier3.text = sortie.missionModifier3
    }

    private fun navigateToSortieDetails() {
        val directions = DashboardViewDirections.actionDashboardViewToSortieView()
        findNavController().navigate(directions)
    }

    private fun showSomeError(throwable: Throwable) {

    }

}