package com.kawaiistudios.warframecompanion.util.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class GreatAdapter<ItemType> : RecyclerView.Adapter<GreatViewHolder<ItemType>>() {

    protected var items: List<ItemType>? = null

    fun getItemAt(index: Int) = items?.get(index)
            ?: throw UninitializedPropertyAccessException("Tried to access adapter item before initializing")

    fun getItemAtOrNull(index: Int) = items?.getOrNull(index)

    fun getItemAtOrDefault(index: Int, default: ItemType) = items?.getOrElse(index) { default }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: GreatViewHolder<ItemType>, position: Int) {
        items?.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    open fun itemComparator(oldItem: ItemType, newItem: ItemType): Boolean {
        return oldItem == newItem
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreatViewHolder<ItemType>

    fun update(items: List<ItemType>) {
        if (this.items == null) {
            this.items = items
            notifyItemRangeInserted(0, items.size)
        } else {
            val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int = itemCount

                override fun getNewListSize(): Int = items.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
                        = this@GreatAdapter.items?.let { itemComparator(it[oldItemPosition], items[newItemPosition]) }
                        ?: false

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)
                        = this@GreatAdapter.items?.let { it[oldItemPosition] == items[newItemPosition] }
                        ?: false
            })

            this@GreatAdapter.items = items
            diff.dispatchUpdatesTo(this)
        }
    }

    fun clear() {
        val itemCount = items?.size ?: 0
        if (itemCount > 0) {
            items = ArrayList()
            notifyItemRangeRemoved(0, itemCount)
        }
    }

}