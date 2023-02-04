package com.app.livrizon.model.publication.body

import com.app.livrizon.model.file.Photo

abstract class PublicationBodyImpl(
    val body_id: Int,
) : java.io.Serializable {
    open fun getText(): String? {
        return null
    }

    open fun description(): String? {
        return null
    }

    open fun title(): String? {
        return null
    }

    open fun address(): Any? {
        return null
    }

    open fun poll(): Any? {
        return null
    }

    open fun link(): Any? {
        return null
    }

    open fun photo(): Photo? {
        return null
    }
}