package com.app.livrizon.fragments.authentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.activities.MainActivity
import com.app.livrizon.databinding.FragmentSplashBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.connection
import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.profile.Account
import com.app.livrizon.model.publication.Post
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.sql.SqlRequest
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.account_pref
import com.app.livrizon.values.connection
import com.app.livrizon.values.token
import kotlinx.coroutines.*


class SplashFragment : CustomFragment() {
    lateinit var binding: FragmentSplashBinding
    lateinit var authenticationRequest: HttpListener
    lateinit var myAccountsRequest: HttpListener
    lateinit var homeRequest: HttpListener
    var currentAuthentication: Authentication? = null
    var myAccounts: MutableList<Authentication>? = null
    private var currentUsername: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun request() {
        myAccountsRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Array<Account> {
                return AuthorizationRequest.getMyAccounts(myAccounts!!.toTypedArray());
            }

            override fun onSuccess(item: Any?) {
                item as Array<Account>
                navController.popBackStack()
                navController.navigate(R.id.myAccountsFragment, Bundle().apply {
                    putSerializable(Parameters.accounts, item)
                })
            }

            override fun onError(error: Any?) {
                navController.popBackStack()
                navController.navigate(R.id.enterFragment)
            }
        }
        homeRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Array<Post> {
                return InitRequest.home()
            }

            override fun onSuccess(item: Any?) {
                item as Array<Post>
                startActivity(
                    Intent(activity, MainActivity::class.java).apply {
                        putExtra(Parameters.posts, item)
                    }
                )
                requireActivity().finish()
            }
        }
        authenticationRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Jwt {
                return AuthorizationRequest.authentication(currentAuthentication!!)
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                token = AccessToken(item.jwt)
                homeRequest.request()
            }

            override fun onError(error: Any?) {
                myAccountsRequest.request()
            }
        }

    }

    override fun initVariable() {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        navController = findNavController()
        connection = connection(requireContext())
        currentUsername = account_pref.getString(Parameters.username, null)
    }

    lateinit var g: CoroutineScope

    override fun init() {
        //CoroutineScope(Dispatchers.IO).launch {
        //    g = this
        //    val time = measureTimeMillis {
        //        val a = async {
        //            log(1, 1)
        //            delay(3000)
        //            log(1, 2)
        //            1
        //        }
        //        val b = async {
        //            log(2, 1)
        //            delay(3000)
        //            log(2, 2)
        //            2
        //        }
        //        log(a.await())
        //    }
        //    log(time)
//
        //}
        CoroutineScope(Dispatchers.IO).launch {
            myAccounts = SqlRequest.getMyAccounts()
            if (currentUsername != null) {
                currentAuthentication =
                    SqlRequest.getAccount(currentUsername!!)
                authenticationRequest.request()
            } else if (myAccounts != null) myAccountsRequest.request()
            else {
                Handler(Looper.getMainLooper()).post {
                    navController.popBackStack()
                    navController.navigate(R.id.enterFragment)
                }
            }
        }
    }
}