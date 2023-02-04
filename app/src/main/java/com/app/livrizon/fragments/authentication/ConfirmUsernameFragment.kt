package com.app.livrizon.fragments.authentication

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R.id.action_confirmUsernameFragment_to_confirmNameFragment
import com.app.livrizon.databinding.FragmentConfirmUsernameBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.values.Parameters

class ConfirmUsernameFragment : CustomFragment() {
    lateinit var binding: FragmentConfirmUsernameBinding
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentConfirmUsernameBinding.inflate(layoutInflater)
        navController = findNavController()
    }

    override fun initButtons() {
        binding.btnNext.setOnClickListener {
            navController.navigate(
                action_confirmUsernameFragment_to_confirmNameFragment,
                Bundle().apply {
                    putString(Parameters.username, binding.edUsername.text.toString())
                })
        }
    }

}