package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Photo

class ArticleBody(
    val title: String,
    val photo: Photo? = null,
    body_id: Int
) : PublicationBodyBase(body_id)