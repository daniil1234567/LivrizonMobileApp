package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentAuthenticationBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.homeRequest
import com.app.livrizon.function.putString
import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.sql.SqlRequest
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.account_pref
import com.app.livrizon.values.token
import kotlinx.coroutines.CoroutineScope

class AuthenticationFragment : CustomFragment() {
    lateinit var binding: FragmentAuthenticationBinding
    lateinit var authenticationRequest: HttpListener
    lateinit var authentication: Authentication
    lateinit var homeRequest: HttpListener
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentAuthenticationBinding.inflate(layoutInflater)
    }

    override fun request() {
        homeRequest = homeRequest(this)
        authenticationRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Jwt {
                return AuthorizationRequest.authentication(authentication)
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                SqlRequest.saveAccount(authentication)
                account_pref.putString(Parameters.username, authentication.username)
                token = AccessToken(item.jwt)
                homeRequest.request()
            }
        }
    }

    override fun initButtons() {
        binding.btnEnter.setOnClickListener {
            authentication =
                Authentication(
                    binding.edUsername.text.toString(),
                    binding.edPassword.text.toString()
                )
            authenticationRequest.request()
        }
        binding.btnRegistration.setOnClickListener {
            navController.navigate(R.id.action_authenticationFragment_to_loginFragment)
        }
        binding.btnForgetPassword.setOnClickListener {
            navController.navigate(R.id.action_authenticationFragment_to_confirmUsernameFragment)
        }
    }
}