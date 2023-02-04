package com.app.livrizon.model.publication

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.MessagePinBody
import com.app.livrizon.model.type.PublicationType

class MessagePin(
    val repost: Boolean,
    publication_id: Int,
    type: PublicationType,
    date: Long,
    override val from: ProfileBase,
    override val body: MessagePinBody,
) : PublicationBase(publication_id,type,date)