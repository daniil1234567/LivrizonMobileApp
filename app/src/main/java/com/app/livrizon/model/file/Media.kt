package com.app.livrizon.model.file

import com.app.livrizon.impl.ScrollImpl
import com.app.livrizon.model.file.statistic.MediaStatistic

class Media(
    val update_id: Int,
    val body: Photo,
    val statistic:MediaStatistic
) : ScrollImpl {
    override fun id(): Number {
        return update_id
    }

    override fun body(): Any {
        return body
    }

    override fun order(): Boolean {
        return true
    }
}