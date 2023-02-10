package com.app.livrizon.model.init

import com.app.livrizon.model.chat.ChatRelation
import com.app.livrizon.model.chat.statistic.ChatStatistic
import com.app.livrizon.model.profile.ChatProfile
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.publication.MessagePin


class InitChat(
    val profile: ChatProfile,
    val statistic: ChatStatistic,
    val relation: ChatRelation,
    val pin: MessagePin? = null,
    val messages: Array<Message>,
) : java.io.Serializable