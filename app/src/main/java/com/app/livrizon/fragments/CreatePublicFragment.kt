package com.app.livrizon.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentCreatePublicBinding
import com.app.livrizon.model.edit.profile.save.TeamSave
import com.app.livrizon.values.Parameters

class CreatePublicFragment : CustomFragment() {
    lateinit var binding: FragmentCreatePublicBinding
    val save = TeamSave()
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentCreatePublicBinding.inflate(layoutInflater)
        navController = findNavController()
    }

    override fun initButtons() {
        binding.btnDone.setOnClickListener {
            save.name = binding.edName.text.toString()
            navController.navigate(R.id.action_createPublicFragment_to_appendMembersFragment,
                Bundle().apply {
                    putSerializable(Parameters.save, save)
                }
            )
        }
    }
}