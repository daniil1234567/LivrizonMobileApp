package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Photo

class MessageReplyBody(
    val description: String? = null,
    val photo: Photo? = null,
    val photos: Int,
    val poll: Boolean,
    val link: Boolean,
    body_id: Int
) : PublicationBodyBase(body_id)