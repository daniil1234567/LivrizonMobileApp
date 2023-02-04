package com.app.livrizon.model.service

import com.app.livrizon.impl.Base

data class Service(
    val service_id: Int,
    val img: Int,
    val text: String
): Base {
    override fun id(): Int {
        return service_id
    }

}