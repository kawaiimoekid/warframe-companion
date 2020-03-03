package com.kawaiistudios.warframecompanion.presentation.market.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
import com.kawaiistudios.warframecompanion.presentation.market.item.buys.MarketItemBuysView
import com.kawaiistudios.warframecompanion.presentation.market.item.details.MarketItemDetailsView
import com.kawaiistudios.warframecompanion.presentation.market.item.sales.MarketItemSalesView
import com.kawaiistudios.warframecompanion.util.adapter.GreatViewPagerAdapter
import kotlinx.android.synthetic.main.view_market_item.*
import javax.inject.Inject

class MarketItemView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MarketItemViewModel

    private val args: MarketItemViewArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_market_item, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MarketItemViewModel::class.java)
        disposable.addAll(
                viewModel.title.subscribe { toolbar.title = it },
                viewModel.isFavorite.subscribe(::showFavoriteIcon)
        )

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.itemFavorite).setOnMenuItemClickListener {
            viewModel.setFavorite(false)
            Toast.makeText(context!!, R.string.view_market_item_toast_removed_from_favorites, Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }
        toolbar.menu.findItem(R.id.itemNotFavorite).setOnMenuItemClickListener {
            viewModel.setFavorite(true)
            Toast.makeText(context!!, R.string.view_market_item_toast_added_to_favorites, Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }

        val pagerAdapter = GreatViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(MarketItemDetailsView.newInstance(args.itemId), getString(R.string.view_market_item_tab_details))
        pagerAdapter.addFragment(MarketItemSalesView.newInstance(args.itemId), getString(R.string.view_market_item_tab_sales))
        pagerAdapter.addFragment(MarketItemBuysView.newInstance(args.itemId), getString(R.string.view_market_item_tab_buys))

        viewPager.offscreenPageLimit = 2
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        viewModel.init(args.itemId)
    }

    private fun showFavoriteIcon(isFavorite: Boolean) {
        toolbar.menu.findItem(R.id.itemFavorite).isVisible = isFavorite
        toolbar.menu.findItem(R.id.itemNotFavorite).isVisible = !isFavorite
    }

}