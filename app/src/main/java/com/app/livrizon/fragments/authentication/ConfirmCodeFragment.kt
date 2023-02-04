package com.app.livrizon.fragments.authentication

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentConfirmCodeBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.type.TokenType
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.token.ConfirmToken
import com.app.livrizon.security.token.RestoreToken
import com.app.livrizon.util.TextListener
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.token

class ConfirmCodeFragment : CustomFragment() {
    lateinit var binding: FragmentConfirmCodeBinding
    lateinit var accountRequest: HttpListener
    var code = 0
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentConfirmCodeBinding.inflate(layoutInflater)
        navController = findNavController()
    }

    override fun request() {
        accountRequest = object : HttpListener(requireContext()) {
            override suspend fun body(): Profile {
                return AuthorizationRequest.account()
            }

            override fun onSuccess(item: Any?) {
                item as Profile
                navController.navigate(R.id.action_confirmCodeFragment_to_confirmAccountFragment,
                    Bundle().apply {
                        putSerializable(Parameters.profile, item)
                    }
                )
            }
        }

        httpListener = object : HttpListener(requireContext()) {
            override suspend fun body(): Jwt {
                return AuthorizationRequest.confirm(code)
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                when (item.type()) {
                    TokenType.confirm -> {
                        token = ConfirmToken(item.jwt)
                        navController.navigate(R.id.action_confirmCodeFragment_to_registrationPasswordFragment)
                    }
                    else -> {
                        token = RestoreToken(item.jwt)
                        accountRequest.request()
                    }
                }
            }
        }
    }

    override fun initListener() {
        textListener = object : TextListener(500) {
            override fun onInputEnd(s: String) {
                if (s.length == 4) {
                    code = s.toInt()
                    httpListener.request()
                }
            }

        }

    }

    override fun init() {
        binding.edCode.addTextChangedListener(textListener)
    }

}