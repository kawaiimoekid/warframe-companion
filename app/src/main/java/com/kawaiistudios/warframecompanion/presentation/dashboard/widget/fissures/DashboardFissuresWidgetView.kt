package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.fissures

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
import kotlinx.android.synthetic.main.view_dashboard_fissures_widget.*
import javax.inject.Inject

class DashboardFissuresWidgetView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DashboardFissuresWidgetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_dashboard_fissures_widget, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DashboardFissuresWidgetViewModel::class.java)
        disposable.add(viewModel.fissures.subscribe(::bind, ::showSomeError))

        btnShowAllFissures.setOnClickListener { navigateToFissuresList() }
    }

    private fun bind(fissures: DashboardFissuresWidgetModel) {
        txtLith.text = getString(R.string.view_dashboard_fissures_widget_txt_lith, fissures.lithCount)
        txtMeso.text = getString(R.string.view_dashboard_fissures_widget_txt_meso, fissures.mesoCount)
        txtNeo.text = getString(R.string.view_dashboard_fissures_widget_txt_neo, fissures.neoCount)
        txtAxi.text = getString(R.string.view_dashboard_fissures_widget_txt_axi, fissures.axiCount)
    }

    private fun navigateToFissuresList() {
        val directions = DashboardViewDirections.actionDashboardViewToFissuresView()
        findNavController().navigate(directions)
    }

    private fun showSomeError(throwable: Throwable) {
        Toast.makeText(context!!, "Some error has occured", Toast.LENGTH_SHORT).show()
    }

}