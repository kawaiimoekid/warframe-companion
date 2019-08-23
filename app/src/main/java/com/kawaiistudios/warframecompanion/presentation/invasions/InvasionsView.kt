package com.kawaiistudios.warframecompanion.presentation.invasions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import kotlinx.android.synthetic.main.view_invasions.*
import javax.inject.Inject

class InvasionsView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: InvasionsViewModel

    private val adapter = InvasionsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_invasions, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(InvasionsViewModel::class.java)

        rvInvasions.adapter = adapter

        disposable.addAll(
                viewModel.invasions.subscribe(adapter::update),
                viewModel.showLoading.subscribe(::showLoading),
                viewModel.showFailure.subscribe(::showFailure)
        )

        btnReload.setOnClickListener { viewModel.refresh() }
    }

    private fun showFailure(show: Boolean) {
        if (show) {
            txtFailure.visibility = VISIBLE
            btnReload.visibility = VISIBLE
        } else {
            txtFailure.visibility = INVISIBLE
            btnReload.visibility = INVISIBLE
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            pbLoading.visibility = VISIBLE
        } else {
            pbLoading.visibility = GONE
        }
    }

}