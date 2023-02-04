package com.app.livrizon.model.publication

import com.app.livrizon.impl.Base
import com.app.livrizon.impl.ChooseImpl
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.body.PublicationBody
import com.app.livrizon.model.publication.reply.MessageReply
import com.app.livrizon.model.publication.statistic.MessageStatistic
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.token

class Message(
    var choose: Boolean = false,
    val reply: MessageReply? = null,
    val repost: Repost? = null,
    val statistic: MessageStatistic,
    publication_id: Int,
    type: PublicationType,
    date: Long,
    override val from: ProfileBase,
    override val body: PublicationBody? = null,
) : PublicationBase(publication_id, type, date), ChooseImpl, Base {

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