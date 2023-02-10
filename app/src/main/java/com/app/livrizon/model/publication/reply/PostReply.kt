package com.app.livrizon.model.publication.reply

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.PublicationBase
import com.app.livrizon.model.publication.body.PostReplyBody
import com.app.livrizon.model.type.PublicationType

class PostReply (
    val body: PostReplyBody,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int
): PublicationBase(publication_id, type, from, date, status)