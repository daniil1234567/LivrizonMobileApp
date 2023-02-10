package com.app.livrizon.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentSubBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.profile.Subscribe
import com.app.livrizon.model.type.event.EventType
import com.app.livrizon.request.*
import com.app.livrizon.values.*
import kotlinx.android.synthetic.main.fragment_enter.*
import kotlinx.android.synthetic.main.item_profile_layout.view.*
import kotlinx.coroutines.CoroutineScope

class SubFragment : CustomFragment() {
    lateinit var binding: FragmentSubBinding
    lateinit var title: String
    lateinit var selection: Selection
    var filter: Filter? = null
    var profileId: Int = 0
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun setBody(
                holder: CustomViewHolder,
                position: Int,
                previous: Base?,
                current: Base,
                next: Base?
            ) {
                holder.itemView.btn_action.visibility = View.GONE
            }

            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                val profile = list[position] as ProfileBase
                navController.navigate(R.id.action_subFragment_to_pageShimmer, Bundle().apply {
                    putInt(Parameters.profile_id, profile.profile_id)
                })
            }
        }
    }

    override fun initListener() {
        webSocketListener = object : WebSocketListener(requireContext(), WebsocketsRoute.sub) {

        }.addParam(Parameters.id, profileId)
            .addParam(Parameters.selection, selection)
            .addParam(Parameters.filter, filter)
            .addParam(Parameters.sort, Sort.def)
            .addListener(EventType.delete, object : WebSocketChanelListener() {
                override fun inputMessage(text: String) {
                    val profiles = gson.fromJson(text, Array<Subscribe>::class.java)
                    recyclerViewAdapter.addListToBottom(*profiles)
                }
            })
    }

    override fun initVariable() {
        binding = FragmentSubBinding.inflate(layoutInflater)
        with(requireArguments()) {
            title = getString(Parameters.title)!!
            selection = getSerializable(Parameters.selection) as Selection
            filter = getSerializable(Parameters.filter) as Filter?
            profileId = requireArguments().getInt(Parameters.profile_id)
        }
        recyclerView = binding.rvProfile
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Array<Subscribe> {
                return InitRequest.sub(selection, profileId, filter)
            }

            override fun onSuccess(item: Any?) {
                item as Array<Subscribe>
                recyclerViewAdapter.addListToBottom(*item)
            }
        }
    }

    override fun init() {
        navController = findNavController()
        recyclerView.adapter = recyclerViewAdapter
        binding.tvToolbat.text = title
    }
}