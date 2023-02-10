package com.app.livrizon.fragments.chat

import com.app.livrizon.adapter.ChatAdapter
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.findPosition
import com.app.livrizon.function.last
import com.app.livrizon.impl.Base
import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.type.event.EventType
import com.app.livrizon.request.*
import com.app.livrizon.values.WebsocketsRoute
import com.app.livrizon.values.gson
import kotlinx.coroutines.CoroutineScope

abstract class MessengerFragmentBase : CustomFragment() {

    override fun initListener() {
        webSocketListener = WebSocketListener(requireContext(), WebsocketsRoute.chats)
            .addListener(EventType.response, object : WebSocketChanelListener() {
                override fun inputMessage(text: String) {
                    val chats = gson.fromJson(text, Array<Chat>::class.java)
                    recyclerViewAdapter.addListToBottom(*chats)
                }
            })
            .addListener(EventType.delete, object : WebSocketChanelListener() {
                override fun inputMessage(text: String) {
                    val ids = gson.fromJson(text, Array<Int>::class.java)
                    recyclerViewAdapter.deleteItem(ids)
                }
            })
            .addListener(EventType.append, object : WebSocketChanelListener() {
                override fun inputMessage(text: String) {
                    val chat = gson.fromJson(text, Chat::class.java)
                    recyclerViewAdapter.replaceItem(chat)
                }
            })
            .addListener(EventType.update, object : WebSocketChanelListener() {
                override fun inputMessage(text: String) {
                    val message = gson.fromJson(text, Message::class.java)
                    recyclerViewAdapter.updateItem(message)
                }
            })
    }

    abstract fun onBodyShortClick(holder: CustomViewHolder, position: Int)
    override fun initAdapter() {
        recyclerViewAdapter = object : ChatAdapter(requireContext()) {
            override fun deleteItem(item: Any) {
                item as Array<Int>
                val chat = list.find {
                    it as Chat
                    item.contains(it.id())
                } as Chat?
                if (chat != null) {
                    object : HttpListener(requireContext()) {
                        override suspend fun body(block: CoroutineScope): Chat {
                            return ChatRequest.chat(chat.equals())
                        }

                        override fun onSuccess(item: Any?) {
                            item as Chat
                            val newList = mutableListOf<Base>()
                            newList.addAll(list)
                            newList.removeIf {
                                it.equals() == item.equals()
                            }
                            newList.add(item)
                            newList.sortByDescending {
                                it.id()
                            }
                            newList.sortByDescending {
                                it as Chat
                                it.relation.attached
                            }
                            setList(*newList.toTypedArray())
                        }
                    }.request()

                }
            }

            override fun updateItem(item: Base) {
                item as Message
                val position = list.findPosition {
                    item.id() == it.id()
                }
                if (position != null) {
                    val chat = list[position] as Chat
                    chat.message = item
                    notifyItemChanged(position)
                }
            }

            override fun replaceItem(item: Base) {
                item as Chat
                if ((list.last() as Chat?)?.relation?.attached != true) {
                    val newList = mutableListOf<Base>()
                    newList.addAll(list)
                    newList.removeIf {
                        item.equals() == it.equals()
                    }
                    newList.add(item)
                    newList.sortByDescending {
                        it as Chat
                        it.relation.attached
                    }
                    setList(*newList.toTypedArray())
                }
            }

            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                this@MessengerFragmentBase.onBodyShortClick(holder, position)
            }


        }
    }

}