package com.halim.aetask.ui.adapter.base

import android.support.v7.widget.RecyclerView


abstract class BaseRecyclerAdapter<in T, VH : BaseViewHolder<T>>(list: List<T> = arrayListOf())
    : RecyclerView.Adapter<VH>() {

    private val list = mutableListOf<T>()

    init {

        this.list.addAll(list)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    open fun setItems(items: List<T>) {
        list.clear()
        list.addAll(items)

        notifyDataSetChanged()
    }

    open fun updateItems(items: List<T>) {
        list.clear()
        list.addAll(items)

        notifyItemRangeChanged(0, items.size)
    }

    open fun addItems(items: List<T>) {
        val oldSize = list.size
        list.addAll(items)

        notifyItemRangeInserted(oldSize, list.size - oldSize)
    }

    override fun getItemCount(): Int = list.size
}