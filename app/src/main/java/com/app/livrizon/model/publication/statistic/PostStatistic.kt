package com.app.livrizon.model.publication.statistic

class PostStatistic(
    val views: Int,
    val forwards: Int,
    val comments: Int,
    reactions: Int
) : PublicationStatisticBase(reactions)