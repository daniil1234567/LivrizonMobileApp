package com.app.livrizon.model.init

import com.app.livrizon.model.chat.ChatRelation
import com.app.livrizon.model.chat.statistic.ChatStatistic
import com.app.livrizon.model.profile.ChatProfile
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.publication.MessagePin

class InitChat(
    val profile: ChatProfile,
    val relation: ChatRelation,
    val pin: MessagePin,
    val statistic: ChatStatistic,
    val messages: Array<Message>,
)