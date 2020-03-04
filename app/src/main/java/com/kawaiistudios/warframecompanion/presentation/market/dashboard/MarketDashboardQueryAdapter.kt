package com.kawaiistudios.warframecompanion.presentation.market.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.util.adapter.GreatAdapter
import com.kawaiistudios.warframecompanion.util.adapter.GreatViewHolder
import kotlinx.android.synthetic.main.adapter_market_dashboard_query.view.*

class MarketDashboardQueryAdapter(
        val onClicked: ((MarketDashboardQueryModel) -> Unit)? = null
) : GreatAdapter<MarketDashboardQueryModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = QueryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_market_dashboard_query, parent, false))

    override fun itemComparator(oldItem: MarketDashboardQueryModel, newItem: MarketDashboardQueryModel)
            = oldItem.id == newItem.id

    inner class QueryViewHolder(view: View)
        : GreatViewHolder<MarketDashboardQueryModel>(view) {

        override fun bind(item: MarketDashboardQueryModel) {
            itemView.txtItemName.text = item.itemName
            itemView.container.setOnClickListener { onClicked?.invoke(item) }
        }

    }

}