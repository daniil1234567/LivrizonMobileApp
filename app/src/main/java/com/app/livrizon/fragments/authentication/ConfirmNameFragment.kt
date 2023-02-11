package com.app.livrizon.fragments.authentication

import android.os.Bundle
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentConfirmNameBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.authorization.ConfirmName
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.token.RestoreToken
import com.app.livrizon.values.Parameters
import com.app.livrizon.request.token
import kotlinx.coroutines.CoroutineScope

class ConfirmNameFragment : CustomFragment() {
    lateinit var binding: FragmentConfirmNameBinding
    lateinit var username: String
    lateinit var confirmName: ConfirmName
    lateinit var accountRequest: HttpListener
    lateinit var confirmRequest: HttpListener
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun request() {
        confirmRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Profile {
                return AuthorizationRequest.account()
            }

            override fun onSuccess(item: Any?) {
                item as Profile
                navController.navigate(R.id.action_confirmNameFragment_to_confirmAccountFragment,
                    Bundle().apply {
                        putSerializable(Parameters.profile, item)
                    }
                )
            }
        }
        initRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Jwt {
                return AuthorizationRequest.confirmName(confirmName)
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                token = RestoreToken(item.jwt)
                accountRequest.request()
            }
        }
    }

    override fun initVariable() {
        binding = FragmentConfirmNameBinding.inflate(layoutInflater)
        username = requireArguments().getString(Parameters.username)!!
    }

    override fun initButtons() {
        binding.btnNext.setOnClickListener {
            confirmName = ConfirmName(username, binding.edName.text.toString())
            confirmRequest.request()
        }
    }
}