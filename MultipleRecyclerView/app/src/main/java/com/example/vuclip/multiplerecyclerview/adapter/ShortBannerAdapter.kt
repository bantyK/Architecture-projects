package com.example.vuclip.multiplerecyclerview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.vuclip.multiplerecyclerview.R

/**
 * Created by Banty on 05/05/18.
 */
class ShortBannerAdapter(private val mSubjectList: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mRowIndex = -1


    fun setRowIndex(index: Int) {
        mRowIndex = index
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.horizontal_list_item_layout_1, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        itemViewHolder.mTextView.text = mSubjectList[position]
        itemViewHolder.mTextView.tag = position

        itemViewHolder.mTextView.setOnClickListener {
            Toast.makeText(holder.mTextView.context,
                    "Position : $mRowIndex,$position",
                    Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return mSubjectList.size
    }

    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mTextView: TextView = itemView.findViewById(R.id.horizontal_item_text)

    }

}
