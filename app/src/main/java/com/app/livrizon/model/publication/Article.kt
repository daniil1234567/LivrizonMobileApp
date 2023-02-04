package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.Owner
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.ArticleBody
import com.app.livrizon.model.publication.statistic.ArticleStatistic
import com.app.livrizon.model.type.PublicationType

class Article(
    val statistic: ArticleStatistic,
    publication_id: Int,
    type: PublicationType,
    date: Long,
    override val from: ProfileBase,
    override val body: ArticleBody,
) : PublicationBase(publication_id,type,date), Base {
    override fun id(): Int {
        return publication_id
    }

    override fun layout(): Int {
        return author
    }
}