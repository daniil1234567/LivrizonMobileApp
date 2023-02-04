package com.app.livrizon.model.publication

import com.app.livrizon.model.profile.Owner
import com.app.livrizon.model.type.PublicationType

class Repost(
    publication_id: Int,
    type: PublicationType,
    date: Long,
    override val from: Owner,
) : PublicationBaseImpl(publication_id, type, date)