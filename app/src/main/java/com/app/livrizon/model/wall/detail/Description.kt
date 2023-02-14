package com.app.livrizon.model.wall.detail

import com.app.livrizon.impl.Base
import com.app.livrizon.model.wall.WallInformation

class Description (
    val text: String
) : Base {
    override fun id(): Int {
        return 2
    }

    override fun layout(): Int {
        return WallInformation.description
    }
}