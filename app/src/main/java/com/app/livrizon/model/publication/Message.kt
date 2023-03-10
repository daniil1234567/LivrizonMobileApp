package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.impl.ChooseImpl
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.PublicationBody
import com.app.livrizon.model.publication.relation.PublicationRelationBase
import com.app.livrizon.model.publication.repost.Repost
import com.app.livrizon.model.publication.statistic.PublicationStatisticBase
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.token

class Message(
    var choose: Boolean = false,
    val body: PublicationBody? = null,
    val reply: PreviewPublication? = null,
    val repost: Repost? = null,
    val relation: PublicationRelationBase,
    val statistic: PublicationStatisticBase,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int
) : PublicationBase(publication_id, type, from, date, status), ChooseImpl, Base {

    override fun id(): Int {
        return publication_id
    }

    override fun layout(): Int {
        if (type != PublicationType.message) return mutual
        return if (from.profile_id == (token as AccessToken).id) {
            if (repost != null) my_message_repost
            else if ((body!!.description?.length ?: 0) > 25 || reply != null) my_message_full
            else my_message
        } else {
            if (repost != null) profile_message_repost
            else if ((body!!.description?.length ?: 0) > 25 || reply != null) profile_message_full
            else profile_message
        }
    }

    override fun choose(): Boolean {
        return choose
    }

}