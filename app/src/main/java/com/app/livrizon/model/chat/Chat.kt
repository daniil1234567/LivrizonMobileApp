package com.app.livrizon.model.chat

import com.app.livrizon.impl.Base
import com.app.livrizon.model.chat.statistic.ChatStatistic
import com.app.livrizon.model.profile.ChatProfile
import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.profile.relation.ProfileRelation
import com.app.livrizon.model.publication.Message

data class Chat(
    val profile: WallProfile,
    val statistic: ChatStatistic,
    val relation: ProfileRelation,
    var message: Message? = null
) : Base {
    override fun id(): Int? {
        return message?.id()
    }

    override fun equals(): Int {
        return profile.profile_id
    }
}