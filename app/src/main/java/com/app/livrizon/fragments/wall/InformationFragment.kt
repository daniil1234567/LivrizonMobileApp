package com.app.livrizon.fragments.wall

import android.view.View
import com.app.livrizon.adapter.InformationAdapter
import com.app.livrizon.databinding.FragmentInformationBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.wall.Description
import com.app.livrizon.model.wall.option.Title
import com.app.livrizon.model.wall.option.WallInformation
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.ProfileRequest
import com.app.livrizon.values.Parameters
import kotlinx.coroutines.CoroutineScope

class InformationFragment : CustomFragment() {
    var profileId = 0
    lateinit var binding: FragmentInformationBinding
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : InformationAdapter(requireContext()) {

        }
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): WallInformation {
                return ProfileRequest.information(profileId)
            }

            override fun onSuccess(item: Any?) {
                item as WallInformation
                with(item) {
                    if (title != null) recyclerViewAdapter.list.add(Title(title!!))
                    if (description != null) recyclerViewAdapter.list.add(Description(description!!))
                    recyclerViewAdapter.updateList()
                }
            }
        }
    }

    override fun initVariable() {
        binding = FragmentInformationBinding.inflate(layoutInflater)
        profileId = requireArguments().getInt(Parameters.profile_id)
        recyclerView = binding.rvInformation
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
    }
}