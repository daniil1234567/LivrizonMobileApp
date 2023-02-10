package com.app.livrizon.adapter

import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.function.loadAvatar
import com.app.livrizon.function.toDate
import com.app.livrizon.model.publication.Post
import kotlinx.android.synthetic.main.item_post_layout.view.*
import kotlinx.android.synthetic.main.item_profile_message_layout.view.img_avatar
import kotlinx.android.synthetic.main.item_profile_message_layout.view.tv_description
import kotlinx.android.synthetic.main.item_profile_message_layout.view.tv_image
import kotlinx.android.synthetic.main.item_profile_message_layout.view.tv_name
import kotlinx.android.synthetic.main.item_profile_message_layout.view.tv_time

abstract class PostAdapter(context: Context) : RecyclerViewAdapterBase(context) {

    override fun getLayout(viewType: Int): Int {
        return R.layout.item_post_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val post = list[position] as Post
        with(holder.itemView) {
            tv_description.text = post.body.description
            tv_name.text = post.from.name
            tv_time.text = post.date.toDate()
            loadAvatar(
                post.from.name,
                tv_image,
                img_avatar,
                post.from.avatar,
                5
            )
            tv_commment.visibility = if (post.comment != null) View.VISIBLE else View.GONE
            img_confirm.visibility = if (post.from.confirm) View.VISIBLE else View.GONE
        }


    }

}