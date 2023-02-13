package com.app.livrizon.model.publication.statistic

class PostStatistic(
    val reposts: Int,
    val replies: Int,
    val comments: Int,
    reactions: Int,
    views: Int,
) : PublicationStatisticBase(reactions, views)