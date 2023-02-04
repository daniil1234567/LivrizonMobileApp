package com.app.livrizon.model.publication.statistic

class MessageStatistic(
    var seen: Boolean,
    val reactions: Int,
    val my_reactions: Int,
) : PublicationStatisticImpl