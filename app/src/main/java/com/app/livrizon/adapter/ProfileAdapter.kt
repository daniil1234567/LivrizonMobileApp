package com.app.livrizon.adapter

import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.function.minuteToMillisecond
import com.app.livrizon.function.loadAvatar
import com.app.livrizon.impl.Base
import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.ProfileBase
import kotlinx.android.synthetic.main.item_append_profile_layout.view.tv_image
import kotlinx.android.synthetic.main.item_profile_layout.view.*
import kotlinx.android.synthetic.main.item_profile_layout.view.img_avatar
import kotlinx.android.synthetic.main.item_profile_layout.view.tv_name

abstract class ProfileAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return when (viewType) {
            ProfileImpl.profile -> R.layout.item_profile_layout
            ProfileImpl.append -> R.layout.item_append_profile_layout
            else -> R.layout.item_profile_base_layout
        }
    }

    override fun initBody(
        holder: CustomViewHolder,
        position: Int,
        previous: Base?,
        current: Base,
        next: Base?
    ) {
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
                context,
                profile.name,
                tv_image,
                img_avatar,
                profile.avatar
            )
        }
    }


}