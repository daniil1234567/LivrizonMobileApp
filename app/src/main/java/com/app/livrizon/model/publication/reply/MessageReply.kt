package com.app.livrizon.model.publication.reply

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.PublicationBase
import com.app.livrizon.model.publication.body.MessageReplyBody
import com.app.livrizon.model.publication.relation.PublicationRelationBase
import com.app.livrizon.model.type.PublicationType

class MessageReply(
    val repost: Boolean,
    val body: MessageReplyBody,
    val relation: PublicationRelationBase? = null,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int,
) : PublicationBase(publication_id, type, from, date, status)