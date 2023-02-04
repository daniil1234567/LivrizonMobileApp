package com.app.livrizon.model.wall.option

import com.app.livrizon.model.wall.InformationImpl
import com.app.livrizon.model.wall.InformationImpl.Companion.education

class Education(
    val education_id: Int,
    val faculty: String,
    val specialization: String,
    val end: Int,
) : InformationImpl {
    override fun id(): Int {
        return education_id
    }

    override fun layout(): Int {
        return education
    }
}
