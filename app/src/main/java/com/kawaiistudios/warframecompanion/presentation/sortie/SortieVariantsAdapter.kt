package com.kawaiistudios.warframecompanion.presentation.sortie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.util.adapter.GreatAdapter
import com.kawaiistudios.warframecompanion.util.adapter.GreatViewHolder

class SortieVariantsAdapter : GreatAdapter<SortieVariantModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = VariantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_sortie_variants, parent, false))

    inner class VariantViewHolder(view: View)
        : GreatViewHolder<SortieVariantModel>(view) {

        override fun bind(item: SortieVariantModel) {

        }

    }

}