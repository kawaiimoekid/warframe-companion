package com.kawaiistudios.warframecompanion.presentation.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.util.adapter.GreatAdapter
import com.kawaiistudios.warframecompanion.util.adapter.GreatViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_news.view.*

class NewsAdapter(
        val callback: (NewsModel) -> Unit
) : GreatAdapter<NewsModel>() {

    private val picasso = Picasso.get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_news, parent, false))

    inner class NewsViewHolder(view: View)
        : GreatViewHolder<NewsModel>(view) {

        override fun bind(item: NewsModel) {
            itemView.cvContainer.setOnClickListener { callback(item) }
            itemView.txtTitle.text = item.message
            picasso.load(item.imageLink)
                    .resize(600, 600)
                    .centerInside()
                    .into(itemView.image)
        }

    }

}