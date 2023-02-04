package com.app.livrizon.model.wall.statistic

open class PageWallStatistic(
    val articles: Int,
    followers: Int? = null,
    publications: Int
) : WallStatistic(followers, publications), PageWallStatisticImpl {
    override fun articles(): Int {
        return articles
    }
}