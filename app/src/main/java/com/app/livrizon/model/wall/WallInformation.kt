package com.app.livrizon.model.wall

import com.app.livrizon.model.wall.detail.*


class WallInformation(
    var title: String? = null,
    var description: String? = null,
    var experiences: List<Experience>? = null,
    var educations: List<Education>? = null,
    var contacts: List<Contact>? = null,
    var skills: List<Skill>? = null,
    var resume: Resume? = null,
) {
    companion object {
        const val title = 0
        const val description = 1
        const val experience = 2
        const val education = 3
        const val contact = 4
        const val skill = 5
        const val resume = 6
    }
}