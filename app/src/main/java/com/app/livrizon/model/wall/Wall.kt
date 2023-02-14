package com.app.livrizon.model.wall

import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.wall.body.WallBody
import com.app.livrizon.model.wall.relation.WallRelation
import com.app.livrizon.model.wall.statistic.WallStatistic


class Wall(
    val wallpaper:String?=null,
    val profile: WallProfile,
    val relation: WallRelation,
    val body: WallBody,
    val statistic: WallStatistic? = null
):java.io.Serializable