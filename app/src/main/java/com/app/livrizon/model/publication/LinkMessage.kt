package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.LinkMessageBody
import com.app.livrizon.model.type.PublicationType

class LinkMessage (
    val body: LinkMessageBody,
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