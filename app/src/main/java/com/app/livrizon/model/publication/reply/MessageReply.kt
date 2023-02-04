package com.app.livrizon.model.publication.reply

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.PublicationBase
import com.app.livrizon.model.publication.body.MessageReplyBody
import com.app.livrizon.model.type.PublicationType

class MessageReply(
    val repost: Boolean,
    update_id: Int,
    type: PublicationType,
    date: Long,
    override val from: ProfileBase,
    override val body: MessageReplyBody,
) : PublicationBase(update_id, type, date)