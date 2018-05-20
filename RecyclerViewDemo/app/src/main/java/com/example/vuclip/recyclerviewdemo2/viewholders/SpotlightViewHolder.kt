package com.example.vuclip.recyclerviewdemo2.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.vuclip.recyclerviewdemo2.R

/**
 * Created by Banty on 15/05/18.
 */
class SpotlightViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var spotlightRecyclerView: RecyclerView? = null

    init {
        spotlightRecyclerView = view.findViewById(R.id.spotlight_recycler_view)
    }
}