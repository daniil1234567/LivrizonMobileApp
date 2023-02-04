package com.app.livrizon.model.wall.statistic

class CompanyWallStatistic(
    val recruiters: Int,
    val vacancies: Int,
    articles: Int,
    followers: Int? = null,
    publications: Int,
) : PageWallStatistic(articles, followers, publications)