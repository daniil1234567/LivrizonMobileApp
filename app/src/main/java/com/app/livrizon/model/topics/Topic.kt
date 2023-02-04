package com.app.livrizon.model.topics

import com.app.livrizon.impl.Base

data class Topic(
    val topic_id: Int? = null,
    val name: String?,
    var choose: Boolean = false
) : Base {
    override fun id(): Int? {
        return topic_id
    }
}