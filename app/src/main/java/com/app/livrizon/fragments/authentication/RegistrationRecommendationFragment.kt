package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentRegistrationRecommendationBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.homeRequest
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.request.HttpListener
import com.app.livrizon.values.Parameters


class RegistrationRecommendationFragment : CustomFragment() {
    lateinit var binding: FragmentRegistrationRecommendationBinding
    lateinit var profiles: Array<Profile>
    lateinit var homeRequest:HttpListener
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentRegistrationRecommendationBinding.inflate(layoutInflater)
        profiles = requireArguments().getSerializable(Parameters.profile) as Array<Profile>
        recyclerView = binding.rvProfile
    }

    override fun request() {
        homeRequest = homeRequest(this)
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {}
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.initList(*profiles)
    }

    override fun initButtons() {
        binding.btnNext.setOnClickListener {
            homeRequest.request()
        }
    }
}