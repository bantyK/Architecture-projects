package com.example.vuclip.multiplerecyclerview.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.vuclip.multiplerecyclerview.R
import com.example.vuclip.multiplerecyclerview.models.Curriculum

/**
 * Created by Banty on 05/05/18.
 */
class MainVerticalRecyclerAdapter(private val mCurriculumList: List<Curriculum>) : RecyclerView.Adapter<MainVerticalRecyclerAdapter.ViewHolder>() {
    private var mContext: Context? = null

    private val LAYOUT_LARGE = 1
    private val LAYOUT_SMALL = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.vertical_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (mCurriculumList.get(position).layout.equals("large", false))
            LAYOUT_LARGE
        else
            LAYOUT_SMALL
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curriculum = mCurriculumList[position]
        holder.title.text = curriculum.year
        if (getItemViewType(position) == 0)
            setUpShortBanner(holder, curriculum, position)
        else
            setUpFullBanner(holder, curriculum, position)
    }

    private fun setUpFullBanner(holder: ViewHolder, curriculum: Curriculum, position: Int) {
        val adapter = FullBannerAdapter(curriculum.subjectList)
        holder.horizontalRecyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.setRowIndex(position)
        holder.horizontalRecyclerView.adapter = adapter
    }

    private fun setUpShortBanner(holder: ViewHolder, curriculum: Curriculum, position: Int) {
        val adapter = ShortBannerAdapter(curriculum.subjectList)
        holder.horizontalRecyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.setRowIndex(position)
        holder.horizontalRecyclerView.adapter = adapter
    }

    override fun getItemCount(): Int {
        return mCurriculumList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView = itemView.findViewById(R.id.title)
        internal var horizontalRecyclerView: RecyclerView = itemView.findViewById(R.id.horizontal_list)
    }
}
