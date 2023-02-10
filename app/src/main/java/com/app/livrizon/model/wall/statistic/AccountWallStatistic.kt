package com.app.livrizon.model.wall.statistic

open class AccountWallStatistic(
    val connections: Int? = null,
    val subscriptions: Int? = null,
    val articles: Int,
    val reviews: Int,
    val services: Int,
    followers: Int? = null,
    publications: Int
) : WallStatistic(followers, publications), PageWallStatisticImpl {
    override fun articles(): Int {
        return articles
    }
}