package com.app.livrizon.fragments.profile

import android.view.View
import com.app.livrizon.databinding.FragmentProfileEditInformationBinding
import com.app.livrizon.fragments.CustomFragment
import kotlinx.android.synthetic.main.fragment_profile_edit_information.*

class EditInformationFragment : CustomFragment() {
    lateinit var binding : FragmentProfileEditInformationBinding

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentProfileEditInformationBinding
.inflate(layoutInflater)
        val tvHeader = binding.tvHeader.text.toString()
    }
}