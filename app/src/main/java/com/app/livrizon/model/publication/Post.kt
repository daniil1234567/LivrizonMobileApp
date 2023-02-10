package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.PublicationBody
import com.app.livrizon.model.publication.relation.PostRelation
import com.app.livrizon.model.publication.reply.PostReply
import com.app.livrizon.model.publication.repost.Repost
import com.app.livrizon.model.publication.statistic.PostStatistic
import com.app.livrizon.model.type.PublicationType

class Post(
    val reply: PostReply? = null,
    val repost: Repost? = null,
    val body: PublicationBody,
    val statistic: PostStatistic,
    val relation: PostRelation? = null,
    val comment: PostComment? = null,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int,
) : PublicationBase(publication_id, type, from, date, status), Base {
    override fun id(): Int {
        return publication_id
    }

    override fun layout(): Int {
        return post
    }
}