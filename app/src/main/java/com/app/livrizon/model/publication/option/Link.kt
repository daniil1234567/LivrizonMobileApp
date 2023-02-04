package com.app.livrizon.model.publication.option

import com.app.livrizon.impl.LinkImpl
import com.app.livrizon.model.file.Photo

class Link(
    val link_id:Int,
    val domain: String,
    val url: String,
    val title: String,
    val description: String? = null,
    val photo: Photo? = null,
) : java.io.Serializable,LinkImpl {
    override fun domain(): String {
        return domain
    }

    override fun url(): String {
        return url
    }

    override fun title(): String {
        return title
    }

    override fun description(): String? {
        return description
    }

    override fun photo(): Photo? {
        return photo
    }
}