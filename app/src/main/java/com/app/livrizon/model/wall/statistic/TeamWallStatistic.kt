package com.app.livrizon.model.wall.statistic

class TeamWallStatistic(
    val admins: Int? = null,
    followers: Int? = null,
    publications: Int
) : WallStatistic(followers, publications),
    PublicWallStatisticImpl {
    override fun admins(): Int? {
        return admins
    }
}