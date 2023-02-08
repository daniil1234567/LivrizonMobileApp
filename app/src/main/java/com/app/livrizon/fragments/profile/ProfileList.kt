package com.app.livrizon.fragments.profile

import android.view.View
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.log
import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.Profile
import kotlinx.android.synthetic.main.item_profile_layout.view.*

class ProfileList(val profiles: Array<Profile>) : CustomFragment() {
    lateinit var binding: FragmentListBinding

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun setBody(
                holder: CustomViewHolder,
                position: Int,
                previous: Base?,
                current: Base,
                next: Base?
            ) {
                holder.itemView.card_last.visibility = View.GONE
            }

            override fun onButtonClick(holder: CustomViewHolder, current: Base, position: Int) {

            }

            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {

            }

        }
    }

    override fun initVariable() {
        binding = FragmentListBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerView
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.initList(*profiles)
    }
}