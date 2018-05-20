package com.example.vuclip.recyclerviewdemo2.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.vuclip.recyclerviewdemo2.R

class FullBannerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    var fullBannerRecyclerView: RecyclerView? = null

    init {
        fullBannerRecyclerView = view.findViewById(R.id.horizontal_recycler_view)
    }
}