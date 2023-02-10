package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.ArticleBody
import com.app.livrizon.model.publication.statistic.PostStatistic
import com.app.livrizon.model.type.PublicationType

open class Article(
    body: ArticleBody,
    statistic: PostStatistic,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int
) : PreviewPost(body, statistic, publication_id, type, from, date, status){
    override fun layout(): Int {
        return article
    }
}