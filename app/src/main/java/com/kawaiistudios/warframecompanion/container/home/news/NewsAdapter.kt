package com.kawaiistudios.warframecompanion.container.home.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kawaiistudios.warframecompanion.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_news.view.*

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val mItems = ArrayList<News.Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder
        = NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_news, parent, false))

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = mItems[position]

        if (item.prop.isNotEmpty()) {
            holder.cv.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.prop))
                context.startActivity(intent)
            }
        }

        Picasso.get().load(item.imageUrl)
                .resize(800, 800)
                .centerInside()
                .into(holder.imgNewsImage)

        val title = item.messages?.firstOrNull { m -> m.languageCode == "en" }?.message ?: ""
        holder.txtNewsTitle.text = title
    }

    fun addItem(event: News.Event) {
        mItems.add(event)
        notifyItemInserted(mItems.size - 1)
    }

    inner class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val cv: CardView = view.adapter_news_card
        val imgNewsImage: ImageView = view.adapter_news_image
        val txtNewsTitle: TextView = view.adapter_news_title
    }
}