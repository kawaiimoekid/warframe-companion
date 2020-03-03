package com.kawaiistudios.warframecompanion.presentation.market.item.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import javax.inject.Inject

class MarketItemDetailsView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MarketItemDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_market_item_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MarketItemDetailsViewModel::class.java)
    }

    companion object {
        private const val ITEM_ID = "itemId"
        fun newInstance(itemId: String) = MarketItemDetailsView().apply {
            arguments = Bundle().apply { putString(ITEM_ID, itemId) }
        }
    }

}