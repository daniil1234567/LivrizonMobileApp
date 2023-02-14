package com.app.livrizon.model.wall.detail

import com.app.livrizon.impl.Base
import com.app.livrizon.model.wall.WallInformation

class Skill(
    val skill_id: Int,
    val skill: String
) : Base {
    override fun id(): Int {
        return skill_id
    }

    override fun layout(): Int {
        return WallInformation.skill
    }
}