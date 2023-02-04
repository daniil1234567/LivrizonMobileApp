package com.app.livrizon.model.wall.option

import com.app.livrizon.model.wall.InformationImpl
import com.app.livrizon.model.wall.InformationImpl.Companion.contact

class Skill(
    val skill_id: Int,
    val skill: String
) : InformationImpl {
    override fun id(): Int {
        return skill_id
    }

    override fun layout(): Int {
        return InformationImpl.skill
    }
}