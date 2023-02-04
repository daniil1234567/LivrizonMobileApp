package com.app.livrizon.fragments.category

import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.adapter.ViewPagerAdapter
import com.app.livrizon.databinding.FragmentHomeBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.Tab
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : CustomFragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var tabs: Array<Tab>
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun init() {
        viewPager2.isSaveEnabled = false
    }

    override fun initVariable() {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        navController = findNavController()
        viewPager2 = binding.viewPager2
        tabLayout = binding.tabPublications
    }

    override fun initAdapter() {
        viewPagerAdapter = ViewPagerAdapter(this)
    }

    override fun initTabLayout() {
        tabs = arrayOf(
            //Tab(
            //    1,
            //    PostListFragment(
            //        requireArguments().getSerializable(Parameters.posts) as Array<Post>,
            //        WALL,
            //        this
            //    ),
            //    "Моя лента"
            //),
            //Tab(2, PostListFragment(null, POSTS_POPULAR, this), "Популярное"),
            //Tab(3, PostListFragment(null, POSTS_NEW, this), "Свежее")
        )
        viewPagerAdapter.serList(*tabs)
        viewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabPublications, viewPager2) { tab, position ->
            tab.text = tabs[position].title
        }.attach()
    }


}