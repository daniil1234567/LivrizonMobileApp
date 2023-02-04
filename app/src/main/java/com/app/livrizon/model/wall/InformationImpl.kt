package com.app.livrizon.model.wall

import com.app.livrizon.impl.Base

interface InformationImpl : Base {
    companion object {
        const val title = 1
        const val description = 2
        const val education = 3
        const val experience = 4
        const val resume = 5
        const val skill = 6
        const val contact = 7
    }
}