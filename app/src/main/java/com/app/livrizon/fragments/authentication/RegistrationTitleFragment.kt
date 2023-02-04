package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentRegistrationTitleBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.edit.profile.save.AccountInformationSave
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.ProfileRequest
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.token

class RegistrationTitleFragment : CustomFragment() {
    lateinit var binding: FragmentRegistrationTitleBinding
    var save = AccountInformationSave()
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentRegistrationTitleBinding.inflate(layoutInflater)
    }

    override fun request() {
        httpListener = object : HttpListener(requireContext()) {
            override suspend fun body(): Jwt {
                return AuthorizationRequest.information(save)
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                token = AccessToken(item.jwt)
                navController.navigate(R.id.action_titleFragment_to_registrationTopicsFragment)
            }
        }
    }

    override fun initButtons() {
        binding.btnNext.setOnClickListener {
            if (binding.edTitle.text.isNotEmpty()) {
                save.title = binding.edTitle.text.toString()
                httpListener.request()
            } else navController.navigate(R.id.action_titleFragment_to_registrationTopicsFragment)
        }
    }
}
