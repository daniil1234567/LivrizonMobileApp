package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Photo

class PreviewPublicationBody(
    val text: String,
    val preview: Photo? = null,
    val media: Boolean,
    val address: Boolean,
    val poll: Boolean,
    body_id: Int,
) : PublicationBodyBase(body_id)