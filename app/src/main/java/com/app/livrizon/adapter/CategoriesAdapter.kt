package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.authorization.Categories
import kotlinx.android.synthetic.main.item_categories_layout.view.*

abstract class CategoriesAdapter(context:Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_categories_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val category = list[position] as Categories
        holder.itemView.tvTitle.text = category.title
        holder.itemView.tvDescription.text = category.descr
        super.onBindViewHolder(holder, position)
    }
}