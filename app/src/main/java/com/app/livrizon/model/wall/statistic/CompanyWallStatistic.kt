package com.app.livrizon.model.wall.statistic

class CompanyWallStatistic(
    val recruiters: Int,
    val vacancies: Int,
    connections: Int? = null,
    subscriptions: Int? = null,
    articles: Int,
    reviews: Int,
    services: Int,
    followers: Int? = null,
    publications: Int,
) : AccountWallStatistic(
    connections,
    subscriptions,
    articles,
    reviews,
    services,
    followers,
    publications
),PageWallStatisticImpl