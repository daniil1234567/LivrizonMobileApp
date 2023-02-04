package com.app.livrizon.fragments.list

import android.view.View
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.adapter.RecyclerViewAdapterImpl
import com.app.livrizon.databinding.FragmentListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base

class ProfileListFragment(
    val init: Array<Base>? = null,
    val webSocketPath: String? = null,
    val recyclerViewAdapterImpl: RecyclerViewAdapterImpl
) : CustomFragment() {
    lateinit var binding: FragmentListBinding
    lateinit var visitAdapter: ProfileAdapter
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        visitAdapter = object : ProfileAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                recyclerViewAdapterImpl.onBodyShortClick(holder, current, position)
            }

            override fun onBodyLongClick(holder: CustomViewHolder, current: Base, position: Int) {
                recyclerViewAdapter.onBodyLongClick(holder, current, position)
            }
        }
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                recyclerViewAdapterImpl.onBodyShortClick(holder, current, position)
            }

            override fun onBodyLongClick(holder: CustomViewHolder, current: Base, position: Int) {
                recyclerViewAdapter.onBodyLongClick(holder, current, position)
            }

            override fun onButtonClick(holder: CustomViewHolder, current: Base, position: Int) {
                recyclerViewAdapterImpl.onButtonClick(holder, current, position)
            }

            override fun setBody(
                holder: CustomViewHolder,
                position: Int,
                previous: Base?,
                current: Base,
                next: Base?
            ) {
                recyclerViewAdapterImpl.setBody(holder, position, previous, current, next)
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