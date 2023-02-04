package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R

abstract class ButtonAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_btn_layout
    }
}