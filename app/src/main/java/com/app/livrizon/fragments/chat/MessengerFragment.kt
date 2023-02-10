package com.app.livrizon.fragments.chat

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.activities.ChatActivity
import com.app.livrizon.activities.CreatePublic
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentMessengerBinding
import com.app.livrizon.function.findPosition
import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.InitMessenger
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.impl.Base
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.token

class MessengerFragment : MessengerFragmentBase() {
    lateinit var binding: FragmentMessengerBinding
    lateinit var visitAdapter: ProfileAdapter
    lateinit var visitRecyclerView: RecyclerView
    lateinit var initMessenger: InitMessenger

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initListener() {
        super.initListener()
        launcher =
            requireActivity().registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
                if (it.resultCode == RESULT_OK) {
                    val profileId = it.data?.getIntExtra(Parameters.profile_id, 0)
                    val unread = it.data?.getIntExtra(Parameters.unread, 0)
                    recyclerViewAdapter.list.findPosition {
                        it.equals() == profileId
                    }.let {
                        if (it != null) {
                            val chat = (recyclerViewAdapter.list[it] as Chat)
                            chat.statistic.unread = unread!!
                            if (chat.id() != (token as AccessToken).id) chat.message?.statistic?.seen =
                                true
                            recyclerViewAdapter.notifyItemChanged(it)
                        }
                    }

                }
            }
    }

    override fun onBodyShortClick(holder: CustomViewHolder, position: Int) {
        val chat = recyclerViewAdapter.list[position] as Chat
        requireContext().startActivity(
            Intent(context, ChatActivity::class.java).apply {
                putExtra(Parameters.profile, chat.profile)
                if (chat.statistic.unread <= 1) putExtra(
                    Parameters.message,
                    chat.message
                )
            }
        )
    }

    override fun initAdapter() {
        super.initAdapter()
        visitAdapter = object : ProfileAdapter(requireContext()) {
            override fun onButtonClick(holder: CustomViewHolder, current: Base) {
                
            }
            override fun setButton(holder: CustomViewHolder, current: Base) {

            }
        }
    }

    override fun initButtons() {
        binding.btnEdit.setOnClickListener {
            requireContext().startActivity(Intent(context, CreatePublic::class.java))
        }
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            override suspend fun body(): InitMessenger {
                return InitRequest.messenger()
            }

            override fun onSuccess(item: Any?) {
                initMessenger = item as InitMessenger
                if (initMessenger.visits.isNotEmpty()) visitAdapter.initList(*initMessenger.visits)
                else binding.containerVisit.visibility = View.GONE
                recyclerViewAdapter.initList(*initMessenger.chats)
            }
        }
    }

    override fun initVariable() {
        binding = FragmentMessengerBinding.inflate(layoutInflater)
        recyclerView = binding.rvChats
        visitRecyclerView = binding.rvRecent
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        visitRecyclerView.adapter = visitAdapter
    }


}

