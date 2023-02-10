package com.app.livrizon.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.function.loadAvatar
import com.app.livrizon.function.toDate
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.publication.PublicationBase.Companion.mutual
import com.app.livrizon.model.publication.PublicationBase.Companion.my_message
import com.app.livrizon.model.publication.PublicationBase.Companion.my_message_full
import com.app.livrizon.model.publication.PublicationBase.Companion.my_message_repost
import com.app.livrizon.model.publication.PublicationBase.Companion.profile_message
import com.app.livrizon.model.publication.PublicationBase.Companion.profile_message_full
import com.app.livrizon.security.Role
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.token
import kotlinx.android.synthetic.main.fragment_chat.view.card_avatar
import kotlinx.android.synthetic.main.item_my_message_layout.view.container_header
import kotlinx.android.synthetic.main.item_my_message_layout.view.ic_view
import kotlinx.android.synthetic.main.item_my_message_layout.view.tv_description
import kotlinx.android.synthetic.main.item_my_message_layout.view.tv_time
import kotlinx.android.synthetic.main.item_my_message_repost_layout.view.*
import kotlinx.android.synthetic.main.item_profile_layout.view.tv_name
import kotlinx.android.synthetic.main.item_profile_message_full_layout.view.*
import kotlinx.android.synthetic.main.item_profile_message_full_layout.view.img_avatar
import kotlinx.android.synthetic.main.item_profile_message_full_layout.view.tv_image


abstract class MessageAdapter(context: Context, val role: Role) : RecyclerViewAdapterBase(context) {

    override fun getLayout(viewType: Int): Int {
        return when (viewType) {
            mutual -> R.layout.item_mutual_message_layout
            my_message -> R.layout.item_my_message_layout
            my_message_full -> R.layout.item_my_message_full_layout
            my_message_repost -> R.layout.item_my_message_repost_layout
            profile_message -> R.layout.item_profile_message_layout
            profile_message_full -> R.layout.item_profile_message_full_layout
            else -> R.layout.item_profile_message_repost_layout
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val message = list[position] as Message
        val previous: Message? = if (position == 0) null else list[position - 1] as Message
        val next: Message? = if (position == list.size - 1) null else list[position + 1] as Message
        with(holder.itemView) {
            with(message) {
                if (holder.id == mutual) {
                    tv_description.text = when (type) {
                        else -> "${from.name} created a chat"
                    }
                } else {
                    body!!
                    tv_description.text = body.description
                    container_header.visibility = View.GONE
                    tv_time.text = date.toDate("HH:mm")
                    if (from.profile_id == (token as AccessToken).id) {
                        if (relation.seen) ic_view.setImageResource(R.drawable.ic_twice_check)
                        else ic_view.setImageResource(R.drawable.ic_single_check)
                    } else {
                        if (role == Role.team) {
                            tv_name.visibility = View.VISIBLE
                            card_avatar.visibility = View.VISIBLE
                            loadAvatar(
                                from.name,
                                holder.itemView.tv_image,
                                holder.itemView.img_avatar,
                                from.avatar
                            )
                        } else {
                            tv_name.visibility = View.GONE
                            card_avatar.visibility = View.GONE
                        }
                    }
                    if (listOf(profile_message_full, my_message_full).contains(holder.id)) {
                        if (reply != null) {
                            with(reply) {
                                container_replay.visibility = View.VISIBLE
                                tv_reply_name.text = from.name
                                tv_replay_description.text = body.description
                            }
                        } else container_replay.visibility = View.GONE
                    } else if (repost != null) {
                        with(repost) {
                            tv_repsot_name.text = from.name
                            tv_repost_date.text = date.toDate()
                            loadAvatar(
                                from.name,
                                holder.itemView.tv_repost_image,
                                holder.itemView.img_repost_avatar,
                                from.avatar
                            )
                        }
                    }
                }
            }
        }
    }

}