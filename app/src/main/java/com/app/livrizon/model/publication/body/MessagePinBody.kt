package com.app.livrizon.model.publication.body

import com.app.livrizon.impl.LinkImpl

class MessagePinBody(
    val description: String?=null,
    val photos:Int,
    val link: Boolean,
    val poll: Boolean,
    body_id: Int
) : PublicationBodyImpl(body_id) {
    override fun link(): Boolean {
        return link
    }

    override fun poll(): Boolean {
        return poll
    }

}