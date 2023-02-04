package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.model.chat.option.AttachmentBase
import com.app.livrizon.model.chat.option.Forward
import com.app.livrizon.model.type.PublicationType
import kotlinx.android.synthetic.main.item_profile_message_layout.view.tv_description
import kotlinx.android.synthetic.main.item_repost_attachment_layout.view.*

abstract class AttachmentAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_repost_attachment_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val attachment = list[position] as AttachmentBase
        with(holder.itemView) {
            if (!attachment.create) {
                attachment.create = true
                this.scaleX = 0f
                this.scaleY = 0f
                this.animate().scaleX(1f).scaleY(1f).duration = 200
            } else{
                this.scaleX = 1f
                this.scaleY = 1f
            }
            when (holder.id) {
                AttachmentBase.repost -> {
                    attachment as Forward
                    attachment.type
                    if (attachment.type == PublicationType.message) tv_description.text =
                        "Сообщения"
                    else tv_description.text = "Публикация"
                    tv_number.text = attachment.publications.size.toString()
                }
            }
        }
    }
}