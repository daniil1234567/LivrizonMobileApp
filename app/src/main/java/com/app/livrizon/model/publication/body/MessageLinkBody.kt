package com.app.livrizon.model.publication.body

import com.app.livrizon.model.publication.option.Link

class MessageLinkBody(
    val description: String?=null,
    val link: Link,
    val links: List<String>,
    body_id: Int
): PublicationBodyImpl(body_id)