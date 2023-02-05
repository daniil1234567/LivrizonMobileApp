package com.app.livrizon.fragments.wall

import android.view.View
import com.app.livrizon.databinding.FragmentDeleteWallBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.loadAvatar
import com.app.livrizon.model.wall.DeleteWall
import com.app.livrizon.security.Status
import com.app.livrizon.values.Parameters

class DeleteWallFragment : CustomFragment() {
    lateinit var binding: FragmentDeleteWallBinding
    lateinit var wall:DeleteWall
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentDeleteWallBinding.inflate(layoutInflater)
        wall = requireArguments().getSerializable(Parameters.posts) as DeleteWall
    }

    override fun init() {
        binding.tvName.text = wall.profile.name
        binding.tvTitle.text = when (wall.profile.status) {
            Status.not_active -> "Аккаунт был удален по пожеланию его владельца"
            else -> "Аккаунт был заблоирован"
        }
        loadAvatar(requireContext(), null, null, binding.imgAvatar, Parameters.deleted, 4)
    }
}