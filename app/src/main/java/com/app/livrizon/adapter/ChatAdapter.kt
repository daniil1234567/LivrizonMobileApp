package com.app.livrizon.adapter

import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.function.loadAvatar
import com.app.livrizon.model.chat.Chat
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.Parameters
import com.app.livrizon.request.token
import kotlinx.android.synthetic.main.item_chat_layout.view.*

open class ChatAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_chat_layout
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val chat = list[position] as Chat
        val profile = chat.profile
        with(holder.itemView) {
            with(chat) {
                with(profile) {
                    img_confirm.visibility = View.GONE
                    if (equals() == (token as AccessToken).id) {
                        loadAvatar(
                            profile.name,
                            holder.itemView.tv_image,
                            holder.itemView.img_avatar,
                            Parameters.favorite,
                            1
                        )
                        tv_name.text = context.getString(R.string.Favorite)
                    } else {
                        if (profile.confirm) img_confirm.visibility = View.VISIBLE
                        else img_confirm.visibility = View.GONE
                        loadAvatar(
                            profile.name,
                            holder.itemView.tv_image,
                            holder.itemView.img_avatar,
                            profile.avatar,
                            5
                        )
                        tv_name.text = name
                    }
                }
                with(message) {
                    if (this != null) {
                        tv_secondary.text = this.body?.description ?: "Чат создан"
                    }
                }
                with(statistic) {
                    tv_unread.text = unread.toString()
                    if (unread > 0) {
                        tv_unread.visibility = View.VISIBLE
                        tv_unread.text = unread.toString()
                    } else tv_unread.visibility = View.GONE
                }
            }
        }
    }


}