package com.kawaiistudios.warframecompanion.container.relics.list

import android.content.Context
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kawaiistudios.warframecompanion.R
import kotlinx.android.synthetic.main.adapter_relics_list.view.*

class RelicsListAdapter(private val context: Context) : RecyclerView.Adapter<RelicsListAdapter.RelicsViewHolder>() {

    private val mItems = ArrayList<Pair<String, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelicsViewHolder
            = RelicsViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_relics_list, parent, false))

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: RelicsViewHolder, position: Int) {
        val item = mItems[position]

        when (item.second) {
            1 -> holder.imgRelic.setImageResource(R.drawable.relic_lith)
            2 -> holder.imgRelic.setImageResource(R.drawable.relic_meso)
            3 -> holder.imgRelic.setImageResource(R.drawable.relic_neo)
            else -> holder.imgRelic.setImageResource(R.drawable.relic_axi)
        }

        holder.txtNewsTitle.text = item.first
    }

    fun addItem(relic: Pair<String, Int>) {
        mItems.add(relic)
        notifyItemInserted(mItems.size - 1)
    }

    inner class RelicsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val cv: CardView = view.adapter_relics_list_card
        val imgRelic: ImageView = view.adapter_relics_list_image
        val txtNewsTitle: TextView = view.adapter_relics_list_name
    }
}