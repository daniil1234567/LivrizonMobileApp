package com.app.livrizon.model.publication

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.PostCommentBody
import com.app.livrizon.model.type.PublicationType

class PostComment(
    val body: PostCommentBody,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int
) : PublicationBase(publication_id, type, from, date, status)