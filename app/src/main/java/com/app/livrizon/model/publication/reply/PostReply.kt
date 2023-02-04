package com.app.livrizon.model.publication.reply

import com.app.livrizon.model.profile.Owner
import com.app.livrizon.model.publication.PublicationBase
import com.app.livrizon.model.publication.body.PostReplyBody
import com.app.livrizon.model.type.PublicationType

class PostReply (
    update_id: Int,
    type: PublicationType,
    date: Long,
    override val from: Owner,
    override val body: PostReplyBody?
): PublicationBase(update_id, type, date)