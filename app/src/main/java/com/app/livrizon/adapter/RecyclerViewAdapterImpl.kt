package com.app.livrizon.adapter

import com.app.livrizon.impl.Base

interface RecyclerViewAdapterImpl {

    fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int)

    fun onBodyLongClick(holder: CustomViewHolder, current: Base, position: Int) {
        onBodyShortClick(holder, current, position)
    }

    fun onButtonClick(holder: CustomViewHolder, current: Base, position: Int)

    fun setBody(
        holder: CustomViewHolder,
        position: Int,
        previous: Base?,
        current: Base,
        next: Base?
    )
}