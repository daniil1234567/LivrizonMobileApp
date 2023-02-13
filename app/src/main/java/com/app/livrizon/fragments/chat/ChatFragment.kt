package com.app.livrizon.fragments.chat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.R
import com.app.livrizon.activities.RepostActivity
import com.app.livrizon.adapter.AttachmentAdapter
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.MessageAdapter
import com.app.livrizon.databinding.FragmentChatBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.*
import com.app.livrizon.impl.Base
import com.app.livrizon.model.chat.attachment.Forward
import com.app.livrizon.model.edit.publication.SaveMessage
import com.app.livrizon.model.init.InitChat
import com.app.livrizon.model.profile.ChatProfile
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.publication.PublicationBase
import com.app.livrizon.model.response.Response
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.model.type.event.EventType
import com.app.livrizon.model.websocket.MessageWebSocket
import com.app.livrizon.request.*
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.*
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.item_my_message_repost_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ChatFragment : CustomFragment() {
    lateinit var binding: FragmentChatBinding
    lateinit var profile: ChatProfile
    lateinit var attachmentAdapter: AttachmentAdapter
    lateinit var attachmentRecyclerView: RecyclerView
    var message_id: Int? = null
    var unread = 0
    var forward: Forward? = null
    var last: Message? = null
    var ids = mutableListOf<Int>()
    override fun getBindingRoot(): View {
        return binding.root
    }

    fun openArrowDown() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(200)
            if (unread != 0) {
                binding.crUnread.visibility = View.VISIBLE
                binding.tvUnread.text = unread.toString()
            } else binding.tvUnread.visibility = View.GONE
            if (!binding.containerDown.isVisible) {
                binding.containerDown.visibility = View.VISIBLE
                binding.containerDown.translationY = 200f.toDp(requireContext())
                binding.containerDown.animate().translationY(0f).duration = 200
            }
        }

    }

    fun closeArrowDown() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(200)
            if (binding.containerDown.isVisible) {
                binding.containerDown.translationY = 0f
                binding.containerDown.animate().translationY(200f.toDp(requireContext()))
                    .withEndAction {
                        binding.containerDown.visibility = View.GONE
                    }.duration = 200
            }
        }
    }

    override fun initAdapter() {
        attachmentAdapter = object : AttachmentAdapter(requireContext()) {

        }
        recyclerViewAdapter = object : MessageAdapter(requireContext(), profile.role) {
            override fun onBodyLongClick(holder: CustomViewHolder, current: Base, position: Int) {
                if (forward == null && holder.id != PublicationBase.mutual && viewModel.action.value != true) {
                    viewModel.action.value = true
                    onBodyShortClick(holder, current, position)
                }
            }

            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                with(holder.itemView) {
                    with(current as Message) {
                        if (holder.id != PublicationBase.mutual) {
                            if (viewModel.action.value == true) {
                                choose = !choose
                                if (choose) {
                                    ids.add(publication_id)
                                    animateTvCount(true)
                                    img_choose.visibility = View.VISIBLE
                                } else {
                                    ids.remove(publication_id)
                                    animateTvCount(false)
                                    if (ids.size == 0) {
                                        Handler().postDelayed({
                                            img_choose.visibility = View.GONE
                                        }, 200)
                                        viewModel.action.value = false
                                    } else {
                                        if (ids.size == 1) binding.btnRepost.visibility =
                                            View.VISIBLE
                                        img_choose.visibility = View.GONE
                                    }
                                }
                                if (ids.size > 1) {
                                    binding.btnReply.animate().alpha(0f).withEndAction {
                                        binding.btnReply.visibility = View.GONE
                                    }.duration = 125
                                } else {
                                    binding.btnReply.visibility = View.VISIBLE
                                    binding.btnReply.animate().alpha(1f).duration = 125
                                }
                            } else choose = false
                        }

                    }
                }
            }

            override fun setBody(
                holder: CustomViewHolder,
                position: Int,
                previous: Base?,
                current: Base,
                next: Base?
            ) {
                val message = list[position] as Message
                with(message) {
                    with(holder.itemView) {
                        if (holder.id != PublicationBase.mutual) {
                            if (viewModel.action.value == true) {
                                if (choose) img_choose.visibility = View.VISIBLE
                                else img_choose.visibility = View.GONE
                                container_choose.translationX = 0f
                            } else {
                                img_choose.visibility = View.GONE
                                container_choose.translationX = (-27f).toDp(context)
                            }
                            viewModel.action.observe(requireActivity()) {
                                if (it != null) {
                                    if (it) {
                                        container_choose.animate().translationX(0f).duration = 200
                                    } else {
                                        if (choose) {
                                            choose = false
                                            Handler().postDelayed({
                                                img_choose.visibility = View.GONE
                                            }, 200)
                                        }
                                        container_choose.animate().translationX(
                                            (-27f).toDp(requireContext())
                                        ).duration = 200
                                    }
                                } else container_choose.translationX = (-27f).toDp(requireContext())
                            }
                        }

                    }
                }

            }

        }
    }

    override fun initListener() {
        viewModel.action.observe(this) {
            if (it != null) {
                if (it) {
                    animateBtnClose(true)
                    binding.toolbarSecondary.visibility = View.VISIBLE
                    binding.toolbarSecondary.alpha = 0f
                    binding.toolbarSecondary.animate().alpha(1f).duration = 200
                } else {
                    animateBtnClose(false)
                    hideSecondaryToolbar()
                    ids.clear()
                }
            }

        }
        webSocketListener = WebSocketListener(requireContext(), WebsocketsRoute.messages).addParam(
            Parameters.id,
            profile.profile_id
        ).addListener(EventType.delete, object : WebSocketChanelListener() {
            override fun inputMessage(text: String) {
                val list = gson.fromJson(text, Array<Int>::class.java)
                val newList = mutableListOf<Base>()
                newList.addAll(recyclerViewAdapter.list)
                newList.removeAll {
                    if (list.contains(it.id())) {
                        ids.remove(it.id())
                        true
                    } else false
                }
                recyclerViewAdapter.setList(*newList.toTypedArray())
            }

        }).addListener(EventType.response, object : WebSocketChanelListener() {
            override fun inputMessage(text: String) {
                val messages = gson.fromJson(text, Array<Message>::class.java)
                if (messages.isNotEmpty()) {
                    val previous = recyclerViewAdapter.list.last()?.id() ?: 0
                    if (messages[0].publication_id > previous) recyclerViewAdapter.addListToBottom(
                        *messages
                    )
                    else recyclerViewAdapter.addListToTop(*messages)
                    last = messages.last()
                }
            }

        }).addListener(EventType.append, object : WebSocketChanelListener() {
            override fun inputMessage(text: String) {
                val messages = gson.fromJson(text, Array<Message>::class.java)
                val from = messages.last().from.profile_id
                val dif =
                    recyclerViewAdapter.itemCount - (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (dif < offset) {
                    recyclerViewAdapter.addListToBottom(*messages)
                    if (from == (token as AccessToken).id || dif < 3) {
                        recyclerView.smoothScrollToPosition(
                            recyclerViewAdapter.itemCount - 1
                        )
                        unread = 0
                        closeArrowDown()
                    } else {
                        unread++
                        openArrowDown()
                    }
                    recyclerViewAdapter.addListToBottom(*messages)
                } else if (from == (token as AccessToken).id) {
                    message_id = null
                    initRequest!!.request()
                } else openArrowDown()
            }

        })

        scrollListener = object : ScrollListener(requireContext()) {
            override fun onScrollStateChanged(newState: Int) {
                if (newState > 0) openArrowDown()
                else closeArrowDown()
            }

            override suspend fun onScrolled(speed: Int, offset: Int, unique: Boolean) {
                if (offset < this@ChatFragment.offset && unique) webSocketListener!!.outputMessage(
                    MessageWebSocket(last, speed < 0)
                )
            }

            override suspend fun onView(position: Int) {
                val message = recyclerViewAdapter.list[position] as Message
                if (!message.relation.seen && message.from.profile_id != (token as AccessToken).id) {
                    message.relation.seen = true
                    unread--
                    PublicationRequest.seen(profile.profile_id, message.id())
                }
            }
        }
        binding.edMessage.doOnTextChanged { text, _, _, _ ->
            if (text!!.isNotEmpty()) {
                binding.btnMicro.visibility = View.GONE
                binding.btnFile.visibility = View.GONE
                binding.btnSend.visibility = View.VISIBLE
            } else {
                binding.btnMicro.visibility = View.VISIBLE
                binding.btnFile.visibility = View.VISIBLE
                binding.btnSend.visibility = View.GONE
            }
        }

    }


    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            val message_id = this@ChatFragment.message_id
            override suspend fun body(block: CoroutineScope): InitChat {
                return InitRequest.chat(profile.profile_id)
            }

            override fun onSuccess(item: Any?) {
                item as InitChat
                recyclerViewAdapter.initList(*item.messages)
                recyclerView.scrollToPosition(
                    if (message_id != null) recyclerViewAdapter.toPosition(message_id)
                    else recyclerViewAdapter.itemCount
                )
            }

        }
    }

    override fun initVariable() {
        binding = FragmentChatBinding.inflate(layoutInflater)
        offset = 15
        recyclerView = binding.rvMessage
        attachmentRecyclerView = binding.rvAttachment
        forward = requireArguments().getSerializable(Parameters.repost) as Forward?
        last = requireArguments().getSerializable(Parameters.message) as Message?
        profile = requireArguments().getSerializable(Parameters.profile) as ChatProfile
    }

    @SuppressLint("SetTextI18n")
    private fun initSecondary() {
        val now = System.currentTimeMillis()
        if (profile.last != null) {
            if (now - profile.last!! < 5 * minute) binding.tvSecondary.text =
                getString(R.string.onlin)
            else if (now - profile.last!! < 10 * minute) binding.tvSecondary.text =
                "в сети недавно"
            else if (now - profile.last!! < 0.5 * hour) binding.tvSecondary.text =
                "в сети ${(now - profile.last!!) / minute} минут назад"
            else if (now - profile.last!! < 2 * hour) binding.tvSecondary.text =
                "в сети час назад"
            else if (now - profile.last!! < day) binding.tvSecondary.text =
                "сегодня в ${profile.last!!.toDate("HH:mm")}"
            else if (now - profile.last!! < 2 * day) binding.tvSecondary.text =
                "вчера в ${profile.last!!.toDate("HH:mm")}"
            else if (now - profile.last!! < week) binding.tvSecondary.text =
                "в сети ${(now - profile.last!!) / day} дней назад"
            else if (now - profile.last!! < year) binding.tvSecondary.text =
                "в сети ${profile.last!!.toDate("dd.MM")}"
            else if (now - profile.last!! < year) binding.tvSecondary.text = "давно"
        } else binding.tvSecondary.text = "${profile.followers} участника"
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        if (last != null) recyclerViewAdapter.initList(last!!)
        attachmentRecyclerView.adapter = attachmentAdapter
        if (forward != null) {
            binding.containerAttached.visibility = View.VISIBLE
            attachmentAdapter.initList(forward!!)
        }
        if (profile.profile_id == (token as AccessToken).id) {
            binding.imgConfirm.visibility = View.GONE
            loadAvatar(
                profile.name,
                binding.tvImage,
                binding.imgAvatar,
                Parameters.favorite
            )
            binding.tvName.text = requireContext().getString(R.string.Favorite)
            binding.tvSecondary.text = "Сохраненные записи"
        } else {
            if (profile.confirm) binding.imgConfirm.visibility = View.VISIBLE
            else binding.imgConfirm.visibility = View.GONE
            loadAvatar(
                profile.name,
                binding.tvImage,
                binding.imgAvatar,
                profile.avatar
            )
            binding.tvName.text = profile.name

        }
        scrollListener.addScrollListener(recyclerView)
    }

    override fun initButtons() {
        binding.btnDown.setOnClickListener {

        }
        binding.btnDelete.setOnClickListener {
            val ids = this.ids
            object : HttpListener(requireContext()) {
                override suspend fun body(block: CoroutineScope): Response {
                    return ChatRequest.deleteMessages(profile.profile_id, ids)
                }
            }.request()
            viewModel.action.value = false
        }
        binding.btnClose.setOnClickListener {
            viewModel.action.value = false
        }
        binding.btnForwardClose.setOnClickListener {
            hideReply()
        }
        binding.btnRepost.setOnClickListener {
            requireContext().startActivity(Intent(context, RepostActivity::class.java).apply {
                putExtra(
                    Parameters.repost, Forward(
                        PublicationType.message, profile.profile_id, false, ids.toTypedArray()
                    )
                )
            })
            viewModel.action.value = false
        }

        binding.btnReply.setOnClickListener {
            openReply()
            hideSecondaryToolbar()
        }

        binding.containerProfile.setOnClickListener {

        }

        binding.btnSend.setOnClickListener {
            val message = SaveMessage(true, null, null, forward, binding.edMessage.text.toString())
            object : HttpListener(requireContext()) {
                override suspend fun body(block: CoroutineScope) {
                    ChatRequest.sendMessage(
                        profile.profile_id,
                        message,
                    )
                }
            }.request()
            binding.edMessage.text.clear()
            forward = null
            hideReply()
            if (viewModel.action.value == true) viewModel.action.value = false
            attachmentAdapter.setList()
            binding.containerAttached.visibility = View.GONE
        }
    }


    private fun hideSecondaryToolbar() {
        binding.toolbarSecondary.alpha = 1f
        binding.toolbarSecondary.animate().alpha(0f).withEndAction {
            binding.toolbarSecondary.visibility = View.GONE
        }.duration = 200
    }

    private fun hideReply() {
        binding.containerForward.animate().translationY(50f.toDp(requireContext())).withEndAction {
            binding.containerForward.visibility = View.GONE
        }.duration = 200
        forward = null
        ids.clear()
    }

    private fun openReply() {
        binding.containerForward.visibility = View.VISIBLE
        binding.containerForward.animate().translationY(0f).duration = 200
        val message = recyclerViewAdapter.list.findLast {
            it.id() == ids[0]
        } as Message
        binding.tvReplyName.text = message.from.name
        binding.tvReplyDescription.text = message.body!!.description
        forward = Forward(PublicationType.message, profile.profile_id, true, arrayOf(message.id()))
        viewModel.action.value = false
    }

    private fun animateBtnClose(plus: Boolean) {
        binding.btnBack.animate().rotation((if (plus) 1 else 0) * 180f).duration = 200
        binding.btnClose.animate().rotation((if (plus) 1 else 0) * 180f).duration = 200
    }


    fun animateTvCount(plus: Boolean) {
        if (ids.size > 0) {
            binding.tvCount.animate()
                .translationY(-1 * (if (plus) 1 else -1) * 25f.toDp(requireContext()))
                .withEndAction {
                    binding.tvCount.text = ids.size.toString()
                    binding.tvCount.translationY = (if (plus) 1 else -1) * 25f
                    binding.tvCount.animate().translationY(
                        0f.toDp(requireContext())
                    ).duration = 75
                }.duration = 75
        }
    }
}