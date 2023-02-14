package com.app.livrizon.model.wall.detail

import com.app.livrizon.impl.Base
import com.app.livrizon.model.wall.WallInformation

class Experience(
    val experience_id: Int,
    val company: String? = null,
    val position: String? = null,
    val description: String? = null,
    val start: String? = null,
    val end: String? = null
) : Base {
    override fun id(): Int {
        return experience_id
    }

    override fun layout(): Int {
        return WallInformation.education
    }
}