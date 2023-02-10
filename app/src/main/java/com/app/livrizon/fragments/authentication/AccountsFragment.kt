package com.app.livrizon.fragments.authentication

import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentAccountsBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.homeRequest
import com.app.livrizon.function.putString
import com.app.livrizon.impl.Base
import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.profile.Account
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.sql.SqlRequest
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.account_pref
import com.app.livrizon.values.token
import kotlinx.android.synthetic.main.item_profile_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountsFragment : CustomFragment() {
    private lateinit var binding: FragmentAccountsBinding
    lateinit var authenticationRequest: HttpListener
    lateinit var homeRequest: HttpListener
    lateinit var accounts: Array<Account>
    lateinit var authentication: Authentication
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentAccountsBinding.inflate(layoutInflater)
        navController = findNavController()
        accounts = requireArguments().getSerializable(Parameters.accounts) as Array<Account>
        recyclerView = binding.rvAccount
    }


    override fun initAdapter() {
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun setButton(holder: CustomViewHolder, current: Base) {
                holder.itemView.tv_button.text = getString(R.string.Enter)
            }

            override fun onButtonClick(holder: CustomViewHolder, current: Base) {
                current as Account
                CoroutineScope(Dispatchers.IO).launch {
                    authentication = SqlRequest.getAccount(current.username)
                    authenticationRequest.request()
                }
            }
        }
    }

    override fun initButtons() {
        binding.btnRegistration.setOnClickListener {
            navController.navigate(R.id.action_myAccountsFragment_to_loginFragment)
        }
        binding.btnEnter.setOnClickListener {
            navController.navigate(R.id.action_myAccountsFragment_to_authenticationFragment)
        }
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

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.initList(*accounts)
    }

}