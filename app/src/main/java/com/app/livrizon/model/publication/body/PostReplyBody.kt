package com.app.livrizon.model.publication.body

import com.app.livrizon.impl.LinkImpl

class PostReplyBody(
    val title: String? = null,
    val description: String? = null,
    val domain: String? = null,
    val url: String? = null,
    body_id: Int
): PublicationBodyBase(body_id),LinkImpl {
    override fun domain(): String? {
        return domain
    }

    override fun url(): String? {
        return url
    }
}