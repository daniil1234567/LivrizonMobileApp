package com.app.livrizon.model.publication.body

class MessageLinkBody(
    val description: String?=null,
    val link: Link,
    val links: List<String>,
    body_id: Int
): PublicationBodyBase(body_id)