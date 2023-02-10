package com.app.livrizon.fragments.profile

import android.os.Bundle
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.adapter.MoveImpl
import com.app.livrizon.adapter.ViewPagerAdapter
import com.app.livrizon.databinding.FragmentProfileSearchBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.fragments.list.ProfileListFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.Tab
import com.app.livrizon.util.TextListener
import com.app.livrizon.values.Parameters
import com.google.android.material.tabs.TabLayoutMediator

class SearchProfileFragment : CustomFragment(), MoveImpl {
    override fun moveToWall(current: Base) {
        navController.navigate(R.id.action_searchProfileFragment_to_pageShimmer, Bundle().apply {
            putInt(Parameters.profile_id, current.equals())
        })
    }

    lateinit var binding: FragmentProfileSearchBinding

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initListener() {
        textListener = object : TextListener(500) {
            override fun onInputEnd(s: String) {
                viewModel.search.value = s
            }

        }

    }

    override fun initAdapter() {
        viewPagerAdapter = ViewPagerAdapter(this)
    }

    override fun initVariable() {
        binding = FragmentProfileSearchBinding.inflate(layoutInflater)
        viewPager2 = binding.viewPager2
        tabLayout = binding.tabProfiles
        tabs = arrayOf(
            Tab(1, SearchProfileListFragment(this), getString(R.string.All)),
            Tab(2, ProfileListFragment(null, null, this), getString(R.string.Peoples)),
            Tab(3, ProfileListFragment(null, null, this), getString(R.string.Сompanies)),
            Tab(4, ProfileListFragment(null, null, this), getString(R.string.Communities)),
            Tab(5, ProfileListFragment(null, null, this), getString(R.string.Teams))
        )
    }

    override fun init() {
        binding.edSearch.addTextChangedListener(textListener)
        viewPager2.isSaveEnabled = false
        viewPager2.adapter = viewPagerAdapter
        viewPagerAdapter.serList(*tabs)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = viewPagerAdapter.list[position].title
        }.attach()
        viewPagerAdapter.updateList()
    }


}


