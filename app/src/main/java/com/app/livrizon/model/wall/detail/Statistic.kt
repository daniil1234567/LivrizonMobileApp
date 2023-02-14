package com.app.livrizon.model.wall.detail

import com.app.livrizon.impl.Base
import com.app.livrizon.request.Filter
import com.app.livrizon.request.Selection

class Statistic(
    val statistic_id: Int,
    val number: Int,
    val statistic: String,
    val selection: Selection? = null,
    val filter: Filter? = null,
) : Base {
    override fun id(): Int {
        return statistic_id
    }

}