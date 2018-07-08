package com.halim.aetask.ui.adapter.decorator

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.halim.aetask.ui.dp2Px


class SpacesItemDecoration(spaceDP: Int, context: Context)
    : RecyclerView.ItemDecoration() {

    private val spacePix: Int = context.dp2Px(spaceDP.toFloat() / 2).toInt()

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?,
                                state: RecyclerView.State?) {
        outRect?.left = spacePix
        outRect?.right = spacePix
        outRect?.top = spacePix
        outRect?.bottom = spacePix
    }
}