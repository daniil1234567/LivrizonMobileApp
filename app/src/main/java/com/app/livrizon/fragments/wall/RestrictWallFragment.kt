package com.app.livrizon.fragments.wall

import android.view.View
import com.app.livrizon.databinding.FragmentRestrictWallBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.wall.Wall
import com.app.livrizon.values.Parameters

class RestrictWallFragment : CustomFragment() {
    lateinit var binding: FragmentRestrictWallBinding
    lateinit var wall: Wall
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun init() {
        binding.tvName.text = wall.profile.name
    }

    override fun initVariable() {
        binding = FragmentRestrictWallBinding.inflate(layoutInflater)
        wall = requireArguments().getSerializable(Parameters.posts) as Wall
    }
}