package com.kawaiistudios.warframecompanion.presentation.market.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding3.widget.textChanges
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.view_market_dashboard.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MarketDashboardView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MarketDashboardViewModel

    private val adapter = MarketDashboardQueryAdapter(::onQueryResultClicked)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_market_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MarketDashboardViewModel::class.java)
        disposable.addAll(
                viewModel.queryResults.subscribe(adapter::update),
                viewModel.showClear.subscribe { btnClear.visibility = if (it) VISIBLE else GONE },
                txtSearch.textChanges()
                        .debounce(150, TimeUnit.MILLISECONDS)
                        .map { it.toString() }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { viewModel.query(it) }
        )

        rvResults.adapter = adapter
        btnClear.setOnClickListener { txtSearch.setText("") }
    }

    private fun onQueryResultClicked(item: MarketDashboardQueryModel) {
        val directions = MarketDashboardViewDirections.actionMarketDashboardViewToMarketItemView(item.id)
        findNavController().navigate(directions)
    }

}