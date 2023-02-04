package com.app.livrizon.fragments.authentication

import android.animation.LayoutTransition
import android.text.InputType
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentLoginBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.authorization.Login
import com.app.livrizon.model.type.ContactType
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.token.TemporaryToken
import com.app.livrizon.values.token
import com.google.android.material.tabs.TabLayout

class LoginFragment : CustomFragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var usernameLayout: LinearLayout
    lateinit var phonePref: LinearLayout
    lateinit var login: Login
    var registration = ContactType.phone
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        navController = findNavController();
        tabLayout = binding.tbRegistration
        usernameLayout = binding.usernameLayout
        phonePref = binding.phonePrefContainer
    }

    override fun init() {
        usernameLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    override fun initAdapter() {

    }

    override fun initListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                registration = ContactType.phone
                binding.edUsername.text.clear()
                binding.edUsername.hint = if (tabLayout.selectedTabPosition == 0) {
                    phonePref.visibility = View.VISIBLE
                    binding.edUsername.inputType = InputType.TYPE_CLASS_PHONE
                    "Номер телефона"
                } else {
                    registration = ContactType.mail
                    phonePref.visibility = View.GONE
                    binding.edUsername.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    "Электоронный адресс"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun request() {
        httpListener = object : HttpListener(requireContext()) {
            override suspend fun body(): Jwt {
                return AuthorizationRequest.login(login)
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                token = TemporaryToken(item.jwt)
                navController.navigate(R.id.action_registrationFragment_to_confirmCodeFragment)
            }
        }
    }

    override fun initButtons() {
        binding.btnGetCode.setOnClickListener {
            login = Login(
                (if (registration == ContactType.phone) "+7" else "") +
                        binding.edUsername.text.toString(),
                registration
            )
            httpListener.request()
        }
    }

}