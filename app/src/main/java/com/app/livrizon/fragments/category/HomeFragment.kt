package com.app.livrizon.fragments.category

import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.adapter.ViewPagerAdapter
import com.app.livrizon.databinding.FragmentHomeBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.fragments.list.PostListFragment
import com.app.livrizon.model.Tab
import com.app.livrizon.model.publication.Post
import com.app.livrizon.values.Parameters
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : CustomFragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var posts: Array<Post>
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
        posts = requireArguments().getSerializable(Parameters.posts) as Array<Post>
    }

    override fun initAdapter() {
        viewPagerAdapter = ViewPagerAdapter(this)
    }

    override fun initTabLayout() {
        tabs = arrayOf(
            Tab(
                1,
                PostListFragment(posts),
                "Моя лента"
            ),
            Tab(2, PostListFragment(posts), "Популярное"),
            Tab(3, PostListFragment(posts), "Свежее")
        )
        viewPagerAdapter.serList(*tabs)
        viewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabPublications, viewPager2) { tab, position ->
            tab.text = tabs[position].title
        }.attach()
    }


}