package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentConfirmAccountBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.response.Response
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.token.RestoreToken
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.token
import kotlinx.android.synthetic.main.item_profile_layout.view.*
import kotlinx.coroutines.CoroutineScope

class ConfirmAccountFragment : CustomFragment() {
    lateinit var binding: FragmentConfirmAccountBinding
    lateinit var profile: Profile
    lateinit var confirmAccountRequest: HttpListener
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun request() {
        confirmAccountRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Response {
                return AuthorizationRequest.confirmAccount()
            }

            override fun onSuccess(item: Any?) {
                navController.navigate(R.id.action_confirmAccountFragment_to_confirmCodeFragment)
            }
        }
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
                holder.itemView.tv_button.text = getString(R.string.Confirm)
            }

            override fun onButtonClick(holder: CustomViewHolder, current: Base) {
                val token = token as RestoreToken
                if (token.confirm)
                    navController.navigate(R.id.action_confirmAccountFragment_to_confirmPasswordFragment)
                else confirmAccountRequest.request()
            }
        }
    }

    override fun initVariable() {
        binding = FragmentConfirmAccountBinding.inflate(layoutInflater)
        recyclerView = binding.rvAccount
        profile = requireArguments().getSerializable(Parameters.profile) as Profile
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.initList(profile)
    }
}