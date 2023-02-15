package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentConfirmPasswordBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.homeRequest
import com.app.livrizon.function.putString
import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.security.token.RestoreToken
import com.app.livrizon.sql.SqlRequest
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.account_pref
import com.app.livrizon.values.token
import kotlinx.coroutines.CoroutineScope

class ConfirmPasswordFragment : CustomFragment() {
    lateinit var binding: FragmentConfirmPasswordBinding
    lateinit var homeRequest: HttpListener
    lateinit var confirmRequest: HttpListener
    lateinit var password: String
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentConfirmPasswordBinding.inflate(layoutInflater)
    }

    override fun request() {
        homeRequest = homeRequest(this)
        confirmRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Jwt {
                val token = token as RestoreToken
                return AuthorizationRequest.authentication(Authentication(token.username, password))
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                val username = (token as RestoreToken).username
                SqlRequest.saveAccount(Authentication(username, password))
                account_pref.putString(Parameters.username, username)
                token = AccessToken(item.jwt)
                homeRequest.request()
            }
        }
    }

    override fun initButtons() {
        binding.btnForgetPassword.setOnClickListener {
            navController.navigate(R.id.action_confirmPasswordFragment_to_registrationPasswordFragment)
        }
        binding.btnContinue.setOnClickListener {
            password = binding.edPassword.text.toString()
            confirmRequest.request()
        }
    }

}