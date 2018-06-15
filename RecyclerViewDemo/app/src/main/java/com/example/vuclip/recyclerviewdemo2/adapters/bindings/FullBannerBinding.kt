package com.example.vuclip.recyclerviewdemo2.adapters.bindings

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.vuclip.recyclerviewdemo2.model.Container

/**
 * Created by Banty on 20/05/18.
 */
class FullBannerBinding {

    companion object {
        @BindingAdapter("bind:short_banner")
        fun bindShortBannerAdapter(recyclerView: RecyclerView, container: Container){

        }
    }

}
