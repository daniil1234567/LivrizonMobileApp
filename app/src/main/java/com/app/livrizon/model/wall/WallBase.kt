package com.app.livrizon.model.wall

import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.wall.relation.WallRelationBase

abstract class WallBase(
    val profile: WallProfile,
) : WallImpl {
    abstract val relation: WallRelationBase
    override fun profile(): WallProfile {
        return profile
    }

    override fun relation(): WallRelationBase {
        return relation
    }
}