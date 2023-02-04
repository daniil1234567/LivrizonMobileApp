package com.app.livrizon.model.wall

import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.wall.relation.DeleteRelationWall

class DeleteWall (
    profile: WallProfile,
    override val relation: DeleteRelationWall
): WallBase(profile)