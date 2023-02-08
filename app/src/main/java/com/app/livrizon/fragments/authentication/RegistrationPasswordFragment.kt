package com.app.livrizon.fragments.authentication

import android.os.Bundle
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentRegistrationPasswordBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.homeRequest
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.type.TokenType
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.token

class RegistrationPasswordFragment : CustomFragment() {
    lateinit var binding: FragmentRegistrationPasswordBinding
    lateinit var homeRequest: HttpListener
    lateinit var updatePasswordRequest: HttpListener
    lateinit var password: String
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentRegistrationPasswordBinding.inflate(layoutInflater)
    }

    override fun request() {
        homeRequest = homeRequest(requireActivity())
        updatePasswordRequest = object : HttpListener(requireContext()) {
            override suspend fun body(): Jwt {
                return AuthorizationRequest.updatePassword(password)
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                token = AccessToken(item.jwt)
                homeRequest.request()
            }
        }
    }

    override fun initButtons() {
        binding.btnNext.setOnClickListener {
            password = binding.edPasswordMain.text.toString()
            if (token.type == TokenType.confirm)
                navController.navigate(
                    R.id.action_registrationPasswordFragment_to_registrationMainInformationFragment,
                    Bundle().apply { putString(Parameters.password, password) })
            else updatePasswordRequest.request()
        }
    }
}