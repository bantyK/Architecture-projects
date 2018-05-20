package com.example.vuclip.recyclerviewdemo2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.vuclip.recyclerviewdemo2.R
import com.example.vuclip.recyclerviewdemo2.model.Container

/**
 * Created by Banty on 16/05/18.
 */
class FullBannerAdapter(private val fullBannerClips: Container) :
        RecyclerView.Adapter<FullBannerAdapter.FullBannerViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullBannerViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.horizontal_single_row, parent, false)
        return FullBannerViewholder(view)
    }

    override fun getItemCount(): Int {
        return fullBannerClips.clips.size
    }

    override fun onBindViewHolder(holder: FullBannerViewholder, position: Int) {
        val clip = fullBannerClips.clips[position]
        holder.title?.text = clip.title
        Glide.with(holder.thumb?.context!!)
                .load(clip.thumb)
                .into(holder.thumb!!)
    }


    class FullBannerViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        var thumb: ImageView? = null
        var title: TextView? = null

        init {
            thumb = view.findViewById(R.id.full_banner_thumb)
            title = view.findViewById(R.id.full_banner_title)
        }
    }
}