package com.app.livrizon.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.livrizon.model.Tab

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    var list: MutableList<Tab> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList() {
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun serList(vararg items: Tab) {
        list = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position].fragment
    }

}