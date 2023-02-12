package com.app.livrizon.model.edit.education

import com.app.livrizon.model.type.education.EducationType


class EducationEdit(
    var education_id: Int?,
    var institution: String,
    var level: EducationType,
    var faculty: String,
    var specialization: String? = null,
    var end: Int? = null,
    var status: Boolean,
) : java.io.Serializable