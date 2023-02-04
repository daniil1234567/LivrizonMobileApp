package com.app.livrizon.model.wall

import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.wall.relation.WallRelationBase

interface WallImpl : java.io.Serializable {
    fun profile(): WallProfile
    fun relation(): WallRelationBase
}