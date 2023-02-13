package com.app.livrizon.model.publication.statistic

class CommentStatistic (
    val forwards:Int,
    reactions: Int,
    views: Int,
): PublicationStatisticBase(reactions, views)