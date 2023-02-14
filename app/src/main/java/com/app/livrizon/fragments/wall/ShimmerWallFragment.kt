package com.app.livrizon.fragments.wall

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentShimmerWallBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.log
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.wall.*
import com.app.livrizon.request.*
import com.app.livrizon.security.Role
import com.app.livrizon.security.Status
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.Parameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

class ShimmerWallFragment : CustomFragment() {
    lateinit var binding: FragmentShimmerWallBinding
    var profileId = 0
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentShimmerWallBinding.inflate(layoutInflater)
        navController = findNavController()
        profileId = requireArguments().getInt(Parameters.profile_id)
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            var mutual: Array<ProfileBase>? = null
            var list: Array<*>? = null
            override suspend fun body(block: CoroutineScope): Wall {
                val wall = ProfileRequest.wall(profileId)
                if (wall.relation.available) {
                    if (wall.statistic != null && (wall.statistic.followers
                            ?: 0) > 0 && wall.profile.profile_id != (token as AccessToken).id
                    ) mutual =
                        withContext(block.coroutineContext) {
                            InitRequest.profilesSub(
                                Selection.connections,
                                Filter.mutual,
                                profileId,
                                Array<ProfileBase>::class.java
                            )
                        }
                    if (wall.profile.role == Role.team && wall.profile.open) {

                    } else if (wall.profile.role != Role.team) list =
                        withContext(block.coroutineContext) {
                            InitRequest.posts(
                                profileId,
                                null,
                                Filter.wall,
                                false,
                                30
                            )
                        }
                }
                return wall
            }

            override fun onSuccess(item: Any?) {
                item as Wall
                navController.popBackStack()
                navController.navigate(
                    if (item.profile.status != Status.active) R.id.deleteWallFragment
                    else if (!item.relation.available) R.id.restrictWallFragment
                    else R.id.wallFragment, Bundle().apply {
                        putSerializable(Parameters.wall, item)
                        putSerializable(Parameters.list, list)
                        putSerializable(Parameters.mutual, mutual)
                    }
                )

            }
        }
    }


}