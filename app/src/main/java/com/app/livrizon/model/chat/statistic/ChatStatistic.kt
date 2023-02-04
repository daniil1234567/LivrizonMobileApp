package com.app.livrizon.model.chat.statistic

class ChatStatistic(
    val followers: Int,
    last: Int? = null,
    attached: Boolean,
    unread: Int
) : ChatStatisticBase(last, attached, unread)