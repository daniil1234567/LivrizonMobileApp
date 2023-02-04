package com.app.livrizon.util

import androidx.recyclerview.widget.DiffUtil
import com.app.livrizon.impl.Base

class DiffUtilCallBack(
    private var oldList: MutableList<Base>,
    private val newList: MutableList<Base>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.equals() == new.equals() && old.layout() == new.layout()
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}