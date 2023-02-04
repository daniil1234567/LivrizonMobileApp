package com.app.livrizon.model.edit.profile.save

import com.app.livrizon.model.edit.contact.ContactEdit
import com.app.livrizon.model.edit.education.EducationEdit
import com.app.livrizon.model.edit.experience.ExperienceEdit
import com.app.livrizon.model.edit.skill.SkillEdit
import com.app.livrizon.model.type.GenderType


class AccountInformationSave : java.io.Serializable {
    var gender: GenderType? = null
    var city_id: Int? = null
    var title: String? = null
    var description: String? = null
    var experiences: List<ExperienceEdit>? = null
    var educations: List<EducationEdit>? = null
    var skills: List<SkillEdit>? = null
    var contacts: List<ContactEdit>? = null
}