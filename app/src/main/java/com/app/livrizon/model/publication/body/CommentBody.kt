package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Media
import com.app.livrizon.model.file.Photo
import com.app.livrizon.model.publication.option.Address
import com.app.livrizon.model.publication.option.Link
import com.app.livrizon.model.publication.option.Poll

class CommentBody(
    val description: String?=null,
    val address: Address? = null,
    val poll: Poll? = null,
    val photos: List<Media>,
    body_id: Int
) : PublicationBodyImpl(body_id) {
    override fun description(): String? {
        return description
    }
    override fun address(): Address? {
        return address
    }

    override fun poll(): Poll? {
        return poll
    }

}