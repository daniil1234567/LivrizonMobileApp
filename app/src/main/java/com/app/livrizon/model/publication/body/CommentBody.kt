package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Media
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.model.wall.Description

class CommentBody(
    val description: String? = null,
    val poll: Poll? = null,
    val address: Address? = null,
    val photos: List<Media>? = null,
    body_id: Int,
) : PublicationBodyBase(body_id)