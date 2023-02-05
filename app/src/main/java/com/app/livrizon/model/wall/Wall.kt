package com.app.livrizon.model.wall

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.wall.body.WallBody
import com.app.livrizon.model.wall.statistic.WallStatistic

abstract class Wall(
    val wallpaper: String? = null,
    val body: WallBody,
    val mutual: Array<ProfileBase>? = null,
    profile: WallProfile
) : WallBase(profile) {
    abstract val statistic: WallStatistic?
    abstract val list: Array<*>?
}