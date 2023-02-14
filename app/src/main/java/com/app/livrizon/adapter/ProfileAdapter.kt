package com.app.livrizon.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.activities.ChatActivity
import com.app.livrizon.function.minuteToMillisecond
import com.app.livrizon.function.loadAvatar
import com.app.livrizon.impl.Base
import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.response.Response
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.ProfileRequest
import com.app.livrizon.security.Role
import com.app.livrizon.values.Parameters
import kotlinx.android.synthetic.main.item_append_profile_layout.view.tv_image
import kotlinx.android.synthetic.main.item_profile_layout.view.*
import kotlinx.android.synthetic.main.item_profile_layout.view.img_avatar
import kotlinx.android.synthetic.main.item_profile_layout.view.tv_name
import kotlinx.coroutines.CoroutineScope

abstract class ProfileAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return when (viewType) {
            ProfileImpl.profile -> R.layout.item_profile_layout
            ProfileImpl.append -> R.layout.item_append_profile_layout
            else -> R.layout.item_profile_base_layout
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val profile = list[position] as ProfileBase
        val name: String
        with(holder.itemView) {
            if (profile is Profile) {
                name = profile.name
                if (profile.confirm) img_confirm.visibility = View.VISIBLE
                else img_confirm.visibility = View.GONE
                if (profile.title != null) {
                    tv_secondary.visibility = View.VISIBLE
                    tv_secondary.text = profile.title
                } else tv_secondary.visibility = View.GONE
                if (profile.last != null && profile.last > System.currentTimeMillis() - 5.minuteToMillisecond()) card_last.visibility =
                    View.VISIBLE
                else card_last.visibility = View.GONE
            } else name = profile.name.split(" ")[0]
            tv_name.text = name
            loadAvatar(
                profile.name,
                tv_image,
                img_avatar,
                profile.avatar
            )
        }
        super.onBindViewHolder(holder, position)
    }

    override fun setButton(holder: CustomViewHolder, current: Base) {
        current as Profile
        holder.itemView.tv_button.text = when (current.role) {
            Role.team -> "Читать"
            else -> when (current.my_sub) {
                0 -> "Подписаться"
                1 -> "Сообщения"
                else -> "Отмена"
            }
        }
    }

    override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
        current as ProfileBase
        moveToWall(current)
    }

    override fun onButtonClick(holder: CustomViewHolder, current: Base) {
        current as Profile
        if (current.my_sub != 1 && current.role!=Role.team) {
            object : HttpListener(context) {
                override suspend fun body(block: CoroutineScope): Response {
                    return ProfileRequest.sub(current.profile_id)
                }

                override fun onSuccess(item: Any?) {
                    current.my_sub = if (current.my_sub==3) 0 else 3
                    setButton(holder, current)
                }
            }.request()
        } else context.startActivity(
            Intent(context, ChatActivity::class.java)
                .putExtra(Parameters.profile, ProfileBase(current))
        )
    }

}