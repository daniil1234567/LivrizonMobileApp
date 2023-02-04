package com.app.livrizon.model.wall.option

import com.app.livrizon.model.wall.InformationImpl

class Title(
    val text: String
) : InformationImpl {
    override fun id(): Int {
        return 1
    }

    override fun layout(): Int {
        return InformationImpl.title
    }
}