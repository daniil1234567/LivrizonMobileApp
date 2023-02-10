package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Media
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.type.PublicationType

class CommentBody(
    val address: Address? = null,
    val photos: List<Media>,
    description: String,
    publication_id: Int,
    type: PublicationType,
    from: ProfileBase,
    date: Long,
    status: Int,
) : PostCommentBody(description, publication_id, type, from, date, status)