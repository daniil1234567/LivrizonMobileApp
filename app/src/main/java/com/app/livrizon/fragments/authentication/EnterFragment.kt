package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentEnterBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.loadImage
import com.squareup.picasso.Picasso

class EnterFragment : CustomFragment() {
    lateinit var binding: FragmentEnterBinding
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentEnterBinding.inflate(layoutInflater)
    }

    override fun initButtons() {
        binding.btnRegistration.setOnClickListener {
            navController.navigate(R.id.action_enterFragment_to_loginFragment)
        }
        binding.btnEnter.setOnClickListener {
            navController.navigate(R.id.action_enterFragment_to_authenticationFragment)
        }
    }
}