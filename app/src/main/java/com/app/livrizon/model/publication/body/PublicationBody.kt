package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Media
import com.app.livrizon.model.publication.option.Address
import com.app.livrizon.model.publication.option.Link
import com.app.livrizon.model.publication.option.Poll

class PublicationBody (
    val title: String? = null,
    val description: String? = null,
    val address: Address? = null,
    val poll: Poll? = null,
    val link: Link? = null,
    val photos: List<Media>? = null,
    body_id: Int
): PublicationBodyImpl(body_id)