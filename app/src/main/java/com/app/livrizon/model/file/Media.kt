package com.app.livrizon.model.file

import com.app.livrizon.impl.Base
import com.app.livrizon.model.file.statistic.MediaStatistic

class Media(
    val update_id: Int,
    val body: Photo,
    val statistic: MediaStatistic
) : Base {
    override fun id(): Int {
        return update_id
    }

}