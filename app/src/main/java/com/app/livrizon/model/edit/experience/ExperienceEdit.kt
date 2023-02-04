package com.app.livrizon.model.edit.experience

class ExperienceEdit(
    var experience_id: Int? = null,
    var company: String,
    var position: String,
    var description: String?,
    var start: String?,
    var end: String? = null,
    var status: Boolean,
) : java.io.Serializable