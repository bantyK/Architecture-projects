package com.example.vuclip.recyclerviewdemo2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.vuclip.recyclerviewdemo2.R
import com.example.vuclip.recyclerviewdemo2.model.Container

/**
 * Created by Banty on 16/05/18.
 */
class SpotlightAdapter(val spotlightContainer: Container) : RecyclerView.Adapter<SpotlightAdapter.SpotlightViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightViewHolder {
        // inflate the spotlight view
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.single_spotlight_view, parent, false)
        return SpotlightViewHolder(view)
    }

    override fun getItemCount(): Int {
        return spotlightContainer.clips.size
    }

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        val context = holder.spotlightThumb?.context
        Glide.with(context!!)
                .load(spotlightContainer.clips[position].thumb)
                .into(holder.spotlightThumb!!)

    }

    class SpotlightViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var spotlightThumb: ImageView? = null

        init {
            spotlightThumb = view.findViewById(R.id.spotlight_thumb)
        }
    }
}