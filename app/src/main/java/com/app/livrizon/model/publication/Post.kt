package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.PublicationBody
import com.app.livrizon.model.publication.reply.PostReply
import com.app.livrizon.model.publication.statistic.PostStatistic
import com.app.livrizon.model.type.PublicationType

class Post(
    val reply: PostReply? = null,
    val repost: Repost? = null,
    val statistic: PostStatistic,
    publication_id: Int,
    type: PublicationType,
    date: Long,
    override val from: ProfileBase,
    override val body: PublicationBody,
) : PublicationBase(publication_id, type, date), Base {
    override fun id(): Int {
        return publication_id
    }

    override fun layout(): Int {
        return post
    }
}