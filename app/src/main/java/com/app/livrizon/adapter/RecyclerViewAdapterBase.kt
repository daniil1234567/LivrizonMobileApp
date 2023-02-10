package com.app.livrizon.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.function.findPosition
import com.app.livrizon.function.log
import com.app.livrizon.impl.Base
import com.app.livrizon.util.DiffUtilCallBack
import kotlinx.android.synthetic.main.item_profile_layout.view.btn_action

abstract class RecyclerViewAdapterBase(val context: Context) :
    RecyclerView.Adapter<CustomViewHolder>(), MoveImpl {
    lateinit var mDiffUtil: DiffUtil.DiffResult
    var list: MutableList<Base> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return list[position].layout()
    }

    abstract fun getLayout(viewType: Int): Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(getLayout(viewType), parent, false)
        return CustomViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val previous = if (position > 0) list[position - 1] else null
        val current = list[position]
        val next = if (position < list.size - 1) list[list.size - 1] else null
        with(holder.itemView) {
            setBody(holder, position, previous, current, next)
            setOnClickListener {
                log("short click $position")
                onBodyShortClick(holder, current, position)
            }
            setOnLongClickListener {
                log("long click $position")
                onBodyLongClick(holder, current, position)
                true
            }
            if(btn_action!=null) {
                setButton(holder,current)
                btn_action.setOnClickListener {
                    onButtonClick(holder, current)
                }
            }
        }
    }

    fun isEmpty(): Boolean {
        return list.size == 0
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initList(vararg items: Base) {
        this.list = items.toMutableList()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList() {
        list.sortBy {
            it.layout()
        }
        list.sortBy {
            it.id()
        }
        notifyDataSetChanged()
    }

    fun toPosition(id: Int): Int {
        for (i in list.indices) {
            val previous = if (i > 0) list[i - 1] else null
            val next = if (i < list.size - 1) list[i + 1] else null
            if ((previous == null || id >= previous.id()!!) && (next == null || id < next.id()!!)) return i
        }
        return list.size - 1
    }

    fun removePosition(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }

    fun removeItem(predicate: (Base) -> Boolean): Boolean {
        val position = list.findPosition {
            predicate(it)
        }
        if (position != null) removePosition(position)
        return position != null
    }

    fun setList(vararg items: Base) {
        mDiffUtil = DiffUtil.calculateDiff(
            DiffUtilCallBack(
                list,
                items.toMutableList()
            )
        )
        list = items.toMutableList()
        mDiffUtil.dispatchUpdatesTo(this)
    }

    private fun addList(position: Int, vararg items: Base) {
        var newList = mutableListOf<Base>()
        newList.addAll(list)
        newList.addAll(position, items.toList())
        newList = newList.reversed().distinctBy {
            it.equals()
        }.reversed().toMutableList()
        setList(*newList.toTypedArray())
    }

    fun addListToTop(vararg items: Base) {
        addList(0, *items)
    }

    open fun replaceItem(item: Base) {

    }

    fun addListToBottom(vararg items: Base) {
        addList(list.size, *items)
    }

    open fun updateItem(item: Base) {

    }

    open fun deleteItem(item: Any) {

    }

    open fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {

    }

    open fun onBodyLongClick(holder: CustomViewHolder, current: Base, position: Int) {

    }

    open fun setBody(
        holder: CustomViewHolder,
        position: Int,
        previous: Base?,
        current: Base,
        next: Base?
    ) {

    }

    open fun onButtonClick(holder: CustomViewHolder, current: Base) {

    }

    open fun setButton(holder: CustomViewHolder, current: Base) {

    }
}