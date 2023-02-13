package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.PreviewPublicationBody
import com.app.livrizon.model.publication.statistic.PublicationStatisticBase
import com.app.livrizon.model.type.PublicationType

class Auth (
    repost: Boolean,
    body: PreviewPublicationBody,
    statistic: PublicationStatisticBase? = null,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int
): PreviewPublication(repost, body, statistic, publication_id, type, from, date, status) {

    override fun layout(): Int {
        return author
    }
}