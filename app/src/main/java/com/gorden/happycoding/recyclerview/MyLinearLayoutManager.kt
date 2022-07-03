package com.gorden.happycoding.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyLinearLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}