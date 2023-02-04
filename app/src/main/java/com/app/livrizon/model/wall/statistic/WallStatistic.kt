package com.app.livrizon.model.wall.statistic

abstract class WallStatistic(
    val followers: Int? = null,
    val publications: Int
) : WallStatisticImpl {
    override fun followers(): Int? {
        return followers
    }

    override fun publications(): Int {
        return publications
    }
}