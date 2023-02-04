package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.model.wall.option.Statistic
import kotlinx.android.synthetic.main.item_statistic_layout.view.*

abstract class StatisticAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_statistic_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val statistic = list[position] as Statistic
        holder.itemView.tv_number.text = statistic.number.toString()
        holder.itemView.tv_statistic.text = statistic.statistic
    }
}