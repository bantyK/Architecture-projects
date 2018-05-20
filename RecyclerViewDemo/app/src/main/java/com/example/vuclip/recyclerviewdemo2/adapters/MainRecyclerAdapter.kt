package com.example.vuclip.recyclerviewdemo2.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vuclip.recyclerviewdemo2.R
import com.example.vuclip.recyclerviewdemo2.model.Container
import com.example.vuclip.recyclerviewdemo2.viewholders.FullBannerViewHolder
import com.example.vuclip.recyclerviewdemo2.viewholders.SpotlightViewHolder

/**
 * Created by Banty on 15/05/18.
 */
class MainRecyclerAdapter(val contentItem: List<Container>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG = "MainRecyclerAdapter"

    //layout types
    val SPOTLIGHT = 1
    val FULL_BANNER = 2

    override fun getItemViewType(position: Int): Int {
        if (contentItem[position].containerType.equals("spotlight", true))
            return SPOTLIGHT
        if (contentItem[position].containerType.equals("full_banner", true))
            return FULL_BANNER
        return FULL_BANNER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var itemView: View? = null
        when (viewType) {
            SPOTLIGHT -> {
                itemView = inflater.inflate(R.layout.spotlight, parent, false)
                return SpotlightViewHolder(itemView)
            }
            FULL_BANNER -> {
                itemView = inflater.inflate(R.layout.horizontal, parent, false)
                return FullBannerViewHolder(itemView)
            }
        }
        return super.createViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return contentItem.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            SPOTLIGHT -> {
                bindSpotlightView(holder, position)
            }
            FULL_BANNER -> {
                bindFullBannerView(holder, position)
            }
        }
    }

    private fun bindSpotlightView(holder: RecyclerView.ViewHolder, position: Int) {
        val spotlightViewHolder = holder as SpotlightViewHolder

        spotlightViewHolder.spotlightRecyclerView?.adapter = SpotlightAdapter(contentItem[position])
        spotlightViewHolder.spotlightRecyclerView?.layoutManager =
                LinearLayoutManager(holder.spotlightRecyclerView?.context, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun bindFullBannerView(holder: RecyclerView.ViewHolder, position: Int) {
        val fullBannerViewHolder = holder as FullBannerViewHolder
        fullBannerViewHolder.fullBannerRecyclerView?.adapter = FullBannerAdapter(contentItem[position])
        fullBannerViewHolder.fullBannerRecyclerView?.layoutManager =
                LinearLayoutManager(holder.fullBannerRecyclerView?.context, LinearLayoutManager.HORIZONTAL, false)
    }
}