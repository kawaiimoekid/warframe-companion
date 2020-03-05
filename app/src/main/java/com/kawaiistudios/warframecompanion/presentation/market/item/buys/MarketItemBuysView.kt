package com.kawaiistudios.warframecompanion.presentation.market.item.buys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import kotlinx.android.synthetic.main.view_market_item_buys.*
import kotlinx.android.synthetic.main.view_market_item_buys.txtNoOrders
import javax.inject.Inject

class MarketItemBuysView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MarketItemBuysViewModel

    private val adapter = MarketItemBuysAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_market_item_buys, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MarketItemBuysViewModel::class.java)
        disposable.addAll(
                viewModel.orders.subscribe(adapter::update),
                viewModel.showNoOrders.subscribe { txtNoOrders.visibility = if (it) View.VISIBLE else View.INVISIBLE },
                viewModel.showError.subscribe { layoutError.visibility = if (it) View.VISIBLE else View.INVISIBLE }
        )

        rvBuys.adapter = adapter

        arguments?.getString(ITEM_ID)?.let { viewModel.init(it) }
    }

    companion object {
        private const val ITEM_ID = "itemId"
        fun newInstance(itemId: String) = MarketItemBuysView().apply {
            arguments = Bundle().apply { putString(ITEM_ID, itemId) }
        }
    }

}