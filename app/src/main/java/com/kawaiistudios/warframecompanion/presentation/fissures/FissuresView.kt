package com.kawaiistudios.warframecompanion.presentation.fissures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.util.injection.Injectable
import kotlinx.android.synthetic.main.view_fissures.*
import javax.inject.Inject

class FissuresView : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: FissuresViewModel

    private val adapter = FissuresAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_fissures, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FissuresViewModel::class.java)

        rvNews.adapter = adapter

        viewModel.fissures.observe(viewLifecycleOwner, Observer { displayFissures(it) })
        viewModel.showFailure.observe(viewLifecycleOwner, Observer { showFailure(it) })
        viewModel.showLoading.observe(viewLifecycleOwner, Observer { showLoading(it) })

        btnReload.setOnClickListener { viewModel.refresh() }
    }

    private fun displayFissures(news: List<FissuresModel>) {
        adapter.update(news)
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