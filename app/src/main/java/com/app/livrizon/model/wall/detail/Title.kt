package com.app.livrizon.model.wall.detail

import com.app.livrizon.impl.Base
import com.app.livrizon.model.wall.WallInformation

class Title(
    val text: String
) : Base {
    override fun id(): Int {
        return 1
    }

    override fun layout(): Int {
        return WallInformation.title
    }
}