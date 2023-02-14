package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.model.wall.detail.Button
import kotlinx.android.synthetic.main.item_btn_layout.view.*

abstract class ButtonAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_btn_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val button = list[position] as Button
        holder.itemView.img_btn.setImageResource(button.img)
        super.onBindViewHolder(holder, position)
    }
}