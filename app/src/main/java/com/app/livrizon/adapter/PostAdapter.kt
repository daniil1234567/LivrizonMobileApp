package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R

abstract class PostAdapter(context: Context) : RecyclerViewAdapterBase(context) {

    override fun getLayout(viewType: Int): Int {
        return R.layout.item_post_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

}