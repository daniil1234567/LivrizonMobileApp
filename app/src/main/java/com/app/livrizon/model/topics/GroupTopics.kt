package com.app.livrizon.model.topics

import com.app.livrizon.impl.Base

class GroupTopics(
    val group_id: Int,
    val title: String,
    val topics: Array<Topic>
) : Base {
    override fun id(): Int {
        return group_id
    }
}