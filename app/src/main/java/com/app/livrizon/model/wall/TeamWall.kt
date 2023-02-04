package com.app.livrizon.model.wall

import com.app.livrizon.model.profile.Member
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.wall.body.WallBody
import com.app.livrizon.model.wall.relation.TeamWallRelation
import com.app.livrizon.model.wall.statistic.TeamWallStatistic

class TeamWall(
    body: WallBody,
    mutual: Array<ProfileBase>? = null,
    profile: WallProfile,
    override val statistic: TeamWallStatistic? = null,
    override val list: Array<Member>? = null,
    override val relation: TeamWallRelation
) : Wall(body, mutual, profile)