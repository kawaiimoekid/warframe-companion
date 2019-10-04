package com.kawaiistudios.warframecompanion.presentation.fissures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import kotlinx.android.synthetic.main.view_fissures.*
import javax.inject.Inject

class FissuresView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: FissuresViewModel

    private val adapter = FissuresAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_fissures, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(FissuresViewModel::class.java)
        disposable.addAll(
                viewModel.fissures.subscribe(adapter::update),
                viewModel.showLoading.subscribe { pbLoading.visibility = if (it) VISIBLE else GONE },
                viewModel.showFailure.subscribe { layoutError.visibility = if (it) VISIBLE else INVISIBLE }
        )

        rvNews.adapter = adapter

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        btnReload.setOnClickListener { viewModel.refresh() }
    }

}