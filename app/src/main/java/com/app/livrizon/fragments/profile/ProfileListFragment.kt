package com.app.livrizon.fragments.profile

import android.view.View
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.MoveImpl
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base

class ProfileListFragment(
    val init: Array<Base>? = null,
    val webSocketPath: String? = null,
    val move: MoveImpl
) : CustomFragment() {
    lateinit var binding: FragmentListBinding
    lateinit var visitAdapter: ProfileAdapter
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        visitAdapter = object : ProfileAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                move.moveToWall(current)
            }
        }
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                move.moveToWall(current)
            }
        }
    }

    override fun initVariable() {
        binding = FragmentListBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerView
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        if (init != null) recyclerViewAdapter.initList(*init)
    }
}