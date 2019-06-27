package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.invasions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.util.adapter.GreatAdapter
import com.kawaiistudios.warframecompanion.util.adapter.GreatViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_dashboard_invasions.view.*

class DashboardInvasionsWidgetAdapter : GreatAdapter<DashboardInvasionsWidgetModel>() {

    private val picasso = Picasso.get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_dashboard_invasions, parent, false))

    override fun itemComparator(oldItem: DashboardInvasionsWidgetModel, newItem: DashboardInvasionsWidgetModel)
            = oldItem.id == newItem.id

    inner class NewsViewHolder(view: View)
        : GreatViewHolder<DashboardInvasionsWidgetModel>(view) {

        override fun bind(item: DashboardInvasionsWidgetModel) {
            itemView.text.text = item.text

            if (item.thumbnailUrl.isNotBlank()) {
                picasso.load(item.thumbnailUrl)
                        .resize(100, 100)
                        .centerInside()
                        .into(itemView.image)
            }
        }

    }

}