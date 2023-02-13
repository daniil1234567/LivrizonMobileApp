package com.app.livrizon.model.publication.body

import com.app.livrizon.impl.LinkImpl
import com.app.livrizon.model.file.Photo

class PostReplyBody(
    val title: String? = null,
    val description: String? = null,
    val domain: String? = null,
    val url: String? = null,
    val photo: Photo? = null,
    val poll: Poll? = null,
    body_id: Int
) : PublicationBodyBase(body_id), LinkImpl {
    override fun domain(): String? {
        return domain
    }

    override fun url(): String? {
        return url
    }

    override fun title(): String? {
        return title
    }

    override fun description(): String? {
        return description
    }

    override fun photo(): Photo? {
        return photo
    }
}