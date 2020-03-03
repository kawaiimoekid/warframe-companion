package com.kawaiistudios.warframecompanion.presentation.market.item.sales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import kotlinx.android.synthetic.main.view_market_item_sales.*
import javax.inject.Inject

class MarketItemSalesView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MarketItemSalesViewModel

    private val adapter = MarketItemSalesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_market_item_sales, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MarketItemSalesViewModel::class.java)
        disposable.add(viewModel.orders.subscribe(adapter::update))

        rvSales.adapter = adapter

        arguments?.getString(ITEM_ID)?.let { viewModel.init(it) }
    }

    companion object {
        private const val ITEM_ID = "itemId"
        fun newInstance(itemId: String) = MarketItemSalesView().apply {
            arguments = Bundle().apply { putString(ITEM_ID, itemId) }
        }
    }

}