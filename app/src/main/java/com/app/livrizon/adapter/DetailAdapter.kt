package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.model.wall.detail.Detail
import kotlinx.android.synthetic.main.item_detail_layout.view.*

abstract class DetailAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_detail_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val detail = list[position] as Detail
        holder.itemView.tv_detail.text = detail.detail
    }
}