package com.app.livrizon.model.publication

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.MessagePinBody
import com.app.livrizon.model.type.PublicationType

class MessagePin(
    val repost: Boolean,
    val body: MessagePinBody,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int,
) : PublicationBase(publication_id, type, from, date, status)