package com.app.livrizon.model.wall.statistic

class CommunityWallStatistic(
    val articles: Int,
    val admins: Int? = null,
    followers: Int? = null,
    publications: Int
) : WallStatistic(followers, publications), PageWallStatisticImpl,
    PublicWallStatisticImpl {
    override fun articles(): Int {
        return articles
    }

    override fun admins(): Int? {
        return admins
    }
}