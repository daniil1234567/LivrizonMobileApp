package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.model.service.Service
import kotlinx.android.synthetic.main.item_service_layout.view.*

abstract class ServiceAdapter(context: Context) :
    RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_service_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val service = list[position] as Service
        holder.itemView.img_service.setImageResource(service.img)
        holder.itemView.tv_secondary.text = service.text
    }
}