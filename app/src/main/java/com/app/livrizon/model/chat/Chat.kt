package com.app.livrizon.model.chat

import com.app.livrizon.impl.Base
import com.app.livrizon.model.chat.statistic.ChatStatistic
import com.app.livrizon.model.profile.ChatProfile
import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.publication.Message

data class Chat(
    val statistic: ChatStatistic,
    val relation: ChatRelation,
    val profile: WallProfile,
    var message: Message? = null
) : Base {
    override fun id(): Int? {
        return message?.id()
    }

    override fun equals(): Int {
        return profile.profile_id
    }
}