package com.app.livrizon.fragments.category

import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.MoveImpl
import com.app.livrizon.adapter.ViewPagerAdapter
import com.app.livrizon.databinding.FragmentNetWorkingBinding
import com.app.livrizon.model.Tab
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.fragments.list.ProfileListFragment
import com.app.livrizon.impl.Base
import com.google.android.material.tabs.TabLayoutMediator

class NetWorkingFragment : CustomFragment(), MoveImpl {
    lateinit var binding: FragmentNetWorkingBinding
    override fun moveToWall(current: Base) {

    }

    override fun getBindingRoot(): View {
        return binding.root
    }


    override fun init() {
        binding.viewPager2.isSaveEnabled = false
        viewPagerAdapter.serList(*tabs)
    }

    override fun initVariable() {
        binding = FragmentNetWorkingBinding.inflate(layoutInflater)
        navController = findNavController()
        viewPager2 = binding.viewPager2
        viewPager2.adapter = recyclerViewAdapter
        tabs = arrayOf(
            Tab(1, ProfileListFragment(null, null, this), getString(R.string.PossibleUser)),
            Tab(2, ProfileListFragment(null, null, this), getString(R.string.Recommend)),
            Tab(3, ProfileListFragment(null, null, this), getString(R.string.Popular))
        )
    }

    override fun initAdapter() {
        viewPagerAdapter = ViewPagerAdapter(this)

    }

    override fun initTabLayout() {
        viewPager2.isSaveEnabled = false
        viewPager2.adapter = viewPagerAdapter
        viewPagerAdapter.serList()
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = viewPagerAdapter.list[position].title
        }.attach()
        viewPagerAdapter.updateList()
    }

}