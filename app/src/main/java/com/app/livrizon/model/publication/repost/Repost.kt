package com.app.livrizon.model.publication.repost

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.PublicationBase
import com.app.livrizon.model.publication.relation.PublicationRelationBase
import com.app.livrizon.model.type.PublicationType

class Repost(
    val relation: PublicationRelationBase,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int
) : PublicationBase(publication_id, type, from, date, status)