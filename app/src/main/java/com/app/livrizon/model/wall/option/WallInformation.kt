package com.app.livrizon.model.wall.option

import ch.qos.logback.classic.db.names.TableName
import com.app.livrizon.model.wall.ResumeBase


class WallInformation(
    var pin_contact: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var experiences: List<Experience>? = null,
    var educations: List<Education>? = null,
    var contacts: List<Contact>? = null,
    var skills: List<Skill>? = null,
    var resume: ResumeBase? = null,
) : java.io.Serializable