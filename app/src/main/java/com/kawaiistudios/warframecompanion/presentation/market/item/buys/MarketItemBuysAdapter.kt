package com.kawaiistudios.warframecompanion.presentation.market.item.buys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.data.web.market.resource.UserStatus
import com.kawaiistudios.warframecompanion.util.adapter.GreatAdapter
import com.kawaiistudios.warframecompanion.util.adapter.GreatViewHolder
import kotlinx.android.synthetic.main.adapter_market_item_sales.view.*

class MarketItemBuysAdapter : GreatAdapter<MarketItemBuysModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = SalesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_market_item_sales, parent, false))

    override fun itemComparator(oldItem: MarketItemBuysModel, newItem: MarketItemBuysModel)
            = oldItem.id == newItem.id

    inner class SalesViewHolder(view: View)
        : GreatViewHolder<MarketItemBuysModel>(view) {

        override fun bind(item: MarketItemBuysModel) {
            itemView.txtUserName.text = item.userName
            itemView.txtPlatinum.text = item.platinum.toString()
            itemView.txtQuantity.text = item.quantity.toString()

            val color = when (item.userStatus) {
                UserStatus.InGame -> R.color.userInGame
                UserStatus.Online -> R.color.userOnline
                UserStatus.Offline -> R.color.userOffline
            }
            itemView.imgStatus.setColorFilter(ContextCompat.getColor(itemView.context, color))

            val status = when (item.userStatus) {
                UserStatus.InGame -> R.string.in_game
                UserStatus.Online -> R.string.online
                UserStatus.Offline -> R.string.offline
            }
            itemView.txtStatus.setText(status)
            itemView.txtStatus.setTextColor(ContextCompat.getColor(itemView.context, color))
        }

    }

}