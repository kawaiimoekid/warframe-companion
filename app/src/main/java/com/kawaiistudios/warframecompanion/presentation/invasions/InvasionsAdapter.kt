package com.kawaiistudios.warframecompanion.presentation.invasions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.util.adapter.GreatAdapter
import com.kawaiistudios.warframecompanion.util.adapter.GreatViewHolder
import kotlinx.android.synthetic.main.adapter_invasions.view.*

class InvasionsAdapter : GreatAdapter<InvasionsModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_invasions, parent, false))

    inner class NewsViewHolder(view: View)
        : GreatViewHolder<InvasionsModel>(view) {

        override fun bind(item: InvasionsModel) {
            itemView.txtTitle.text = item.node
            itemView.imgAttacker.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.attackerIcon))
            itemView.imgAttacker.setColorFilter(ContextCompat.getColor(itemView.context, item.attackerIconColor))
            itemView.txtAttackerReward.text = item.attackerReward
            itemView.imgDefender.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.attackerIcon))
            itemView.imgDefender.setColorFilter(ContextCompat.getColor(itemView.context, item.attackerIconColor))
            itemView.txtDefenderReward.text = item.attackerReward
        }

    }

}