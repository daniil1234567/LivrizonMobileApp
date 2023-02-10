package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.MessageReplyBody
import com.app.livrizon.model.type.PublicationType

class MessageLink (
    val body: MessageReplyBody,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int
): PublicationBase(publication_id, type, from, date, status), Base {
    override fun id(): Int {
        return publication_id
    }

    override fun layout(): Int {
        return link
    }

}