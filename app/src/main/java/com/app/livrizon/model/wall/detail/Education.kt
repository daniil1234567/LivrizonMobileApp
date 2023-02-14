package com.app.livrizon.model.wall.detail

import com.app.livrizon.impl.Base
import com.app.livrizon.model.wall.WallInformation

class Education(
    val education_id: Int,
    val faculty: String,
    val specialization: String,
    val end: Int,
) : Base {
    override fun id(): Int {
        return education_id
    }

    override fun layout(): Int {
        return WallInformation.education
    }
}
