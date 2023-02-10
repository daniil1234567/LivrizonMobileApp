package com.app.livrizon.model.publication.body

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.PublicationBase
import com.app.livrizon.model.type.PublicationType

open class PreviewCommentBody(
    val description:String,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int
) : PublicationBase(publication_id, type, from, date, status)