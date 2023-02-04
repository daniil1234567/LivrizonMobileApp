package com.app.livrizon.model.chat.statistic

open class ChatStatisticBase(
    val last: Int?=null,
    val attached: Boolean,
    var unread: Int,
) : java.io.Serializable