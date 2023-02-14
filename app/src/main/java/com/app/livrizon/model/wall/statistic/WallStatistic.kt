package com.app.livrizon.model.wall.statistic

class WallStatistic(
    val followers: Int? = null,
    val subscriptions: Int? = null,
    val connections: Int? = null,
    val admins: Int? = null,
    val publications: Int,
    val media: Int,
    val articles: Int,
    val reviews: Int,
    val services: Int,
    val recruiters: Int,
    val vacancies: Int,
) : java.io.Serializable