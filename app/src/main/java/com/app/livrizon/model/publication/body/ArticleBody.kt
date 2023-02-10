package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Photo

class ArticleBody(
    val title: String,
    val photo: Photo? = null,
    body_id: Int,
) : PublicationBodyImpl(body_id) {

    override fun title(): String {
        return title
    }

    override fun photo(): Photo? {
        return photo
    }
}