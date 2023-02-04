package com.app.livrizon.fragments.chat

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentRepsotBinding
import com.app.livrizon.model.chat.Chat
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest
import com.app.livrizon.values.Parameters

class RepostFragment : MessengerFragmentBase() {
    lateinit var binding: FragmentRepsotBinding
    override fun onBodyShortClick(holder: CustomViewHolder, position: Int) {
        val chat = recyclerViewAdapter.list[position] as Chat
        navController.navigate(R.id.chatFragment, Bundle().apply {
            putSerializable(Parameters.profile, chat.profile)
            putSerializable(Parameters.message, chat.message)
            putAll(requireArguments())
        })
    }

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentRepsotBinding.inflate(layoutInflater)
        navController = findNavController()
        recyclerView = binding.rvChats
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun request() {
        object : HttpListener(requireContext()) {
            override suspend fun body(): Array<Chat> {
                return InitRequest.chats()
            }

            override fun onSuccess(item: Any?) {
                item as Array<Chat>
                recyclerViewAdapter.initList(*item)
            }
        }.request()
    }
}