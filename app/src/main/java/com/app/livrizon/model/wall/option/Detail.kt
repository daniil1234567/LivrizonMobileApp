package com.app.livrizon.model.wall.option

import com.app.livrizon.impl.Base

class Detail(
    val detail_id: Int,
    val img: Int,
    val detail: String,
) : Base {
    override fun id(): Int {
        return detail_id
    }

    companion object {
        const val city = 1
        const val contact = 2
        const val more = 3
    }
}