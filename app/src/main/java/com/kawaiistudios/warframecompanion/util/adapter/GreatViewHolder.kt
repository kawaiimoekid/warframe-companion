package com.kawaiistudios.warframecompanion.util.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class GreatViewHolder<ItemType>(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: ItemType) { }

}