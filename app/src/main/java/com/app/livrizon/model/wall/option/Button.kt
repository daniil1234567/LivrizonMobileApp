package com.app.livrizon.model.wall.option

import com.app.livrizon.impl.Base

class Button(
    val button_id: Int,
    val img: Int
) : Base {
    override fun id(): Int {
        return button_id
    }

    companion object {
        const val chat = 1
        const val subscribe = 2
    }
}