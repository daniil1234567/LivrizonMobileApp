package com.app.livrizon.model.chat.option

import com.app.livrizon.model.type.PublicationType

class Forward(
    val type: PublicationType,
    val from: Int,
    val reply: Boolean,
    val publications: Array<Int>
) :
    AttachmentBase(1) {
    override fun layout(): Int {
        return repost
    }
}