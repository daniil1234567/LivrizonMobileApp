package com.app.livrizon.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.model.service.Service
import kotlinx.android.synthetic.main.item_service_layout.view.*

abstract class ServiceAdapter(context: Context) :
    RecyclerViewAdapterBase(context) {
    var services = mutableListOf<Service>()

    override fun getLayout(viewType: Int): Int {
        return R.layout.item_service_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val service = services[position]
        holder.itemView.img_service.setImageResource(service.img)
        holder.itemView.tv_secondary.text = service.text
        holder.itemView.body.setOnClickListener {
            onClick(service.service_id)
        }
    }

    override fun getItemCount(): Int {
        return services.size
    }
    abstract fun onClick(key: Int)
    @SuppressLint("NotifyDataSetChanged")
    fun setList(vararg list:Service){
        services= list.toMutableList()
        notifyDataSetChanged()
    }
}