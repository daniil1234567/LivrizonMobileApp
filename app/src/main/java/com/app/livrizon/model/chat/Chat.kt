package com.app.livrizon.model.chat

import com.app.livrizon.impl.Base
import com.app.livrizon.model.chat.statistic.ChatStatisticBase
import com.app.livrizon.model.profile.ChatProfile
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.publication.MessagePin

data class Chat(
    val statistic: ChatStatisticBase,
    val relation: ChatRelation,
    val pin: MessagePin? = null,
    val profile: ChatProfile,
    var message: Message? = null
) : Base {
    override fun id(): Int? {
        return message?.id()
    }

    override fun equals(): Int {
        return profile.profile_id
    }
}