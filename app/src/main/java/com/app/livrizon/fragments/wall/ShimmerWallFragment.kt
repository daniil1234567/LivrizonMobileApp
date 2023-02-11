package com.app.livrizon.fragments.wall

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentShimmerWallBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.response.WallResponse
import com.app.livrizon.model.wall.*
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.ProfileRequest
import com.app.livrizon.security.Role
import com.app.livrizon.security.Status
import com.app.livrizon.values.Parameters
import com.app.livrizon.request.gson
import kotlinx.coroutines.CoroutineScope

class ShimmerWallFragment : CustomFragment() {
    lateinit var binding: FragmentShimmerWallBinding
    var profileId = 0
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentShimmerWallBinding.inflate(layoutInflater)
        navController=findNavController()
        profileId = requireArguments().getInt(Parameters.profile_id)
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): WallResponse {
                return ProfileRequest.wall(profileId)
            }

            override fun onSuccess(item: Any?) {
                item as WallResponse
                val bundle = Bundle().apply {
                    putSerializable(
                        Parameters.posts, gson.fromJson(
                            item.body,
                            if (item.status!=Status.active)
                                DeleteWall::class.java
                            else if (item.role == Role.user) UserWall::class.java
                            else if (item.role == Role.company) CompanyWall::class.java
                            else if (item.role == Role.community) CommunityWall::class.java
                            else TeamWall::class.java
                        )
                    )
                }
                navController.popBackStack()
                navController.navigate(
                    if (item.status!=Status.active) R.id.deleteWallFragment
                    else if (!item.available) R.id.restrictWallFragment
                    else R.id.wallFragment, bundle
                )

            }
        }
    }


}