package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.MessageReplyBody
import com.app.livrizon.model.type.PublicationType

class MessageLink (
    type: PublicationType,
    publication_id: Int,
    date: Long,
    override val from: ProfileBase,
    override val body: MessageReplyBody,
): PublicationBase(publication_id,type,date), Base {
    override fun id(): Int {
        return publication_id
    }

    override fun layout(): Int {
        return link
    }

}