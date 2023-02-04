package com.app.livrizon.model.wall

import com.app.livrizon.model.wall.InformationImpl.Companion.description

class Description(
    val text: String
) : InformationImpl {
    override fun id(): Int {
        return 1
    }

    override fun layout(): Int {
        return description
    }
}