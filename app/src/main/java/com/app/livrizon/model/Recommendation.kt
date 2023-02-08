package com.app.livrizon.model

import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.Profile

class Recommendation(
    val recommendation_id: Int,
    val header: String,
    val recommendation: Array<Profile>
) : Base {
    override fun id(): Int {
        return recommendation_id
    }
}