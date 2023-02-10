package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Photo

class AuthorBody(
    val title: String,
    body_id: Int,
) : PublicationBodyImpl(body_id) {
    override fun title(): String {
        return title
    }

    override fun address(): Address? {
        return null
    }

    override fun poll(): Poll? {
        return null
    }

    override fun link(): Link? {
        return null
    }

    override fun photo(): Photo? {
        return null
    }
}