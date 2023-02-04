package com.app.livrizon.fragments.list

import android.view.View
import com.app.livrizon.adapter.PostAdapter
import com.app.livrizon.databinding.FragmentListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.publication.Post

class PostListFragment(val posts: Array<Post>? = null) : CustomFragment() {
    lateinit var binding: FragmentListBinding
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentListBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerView
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : PostAdapter(requireContext()) {
        }
    }

    override fun init() {
        if (posts != null) recyclerViewAdapter.setList(*posts)
        recyclerView.adapter = recyclerViewAdapter
    }
}