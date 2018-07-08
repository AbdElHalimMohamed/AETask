package com.halim.aetask.ui.adapter.base

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BaseViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {

    protected val resources: Resources = view.resources

    abstract fun bind(model: T)
}