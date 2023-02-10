package com.app.livrizon.model.publication

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.type.PublicationType


abstract class PublicationBase(
    val publication_id: Int,
    val type: PublicationType,
    val from: ProfileBase,
    val date: Long,
    val status: Int,
) : java.io.Serializable {
    companion object {
        const val mutual = 1
        const val popular = 2
        const val article = 3
        const val author = 4
        const val post = 7
        const val comment = 8
        const val link = 9
        const val my_message_full = 10
        const val my_message_repost = 11
        const val my_message = 12
        const val profile_message_full = 13
        const val profile_message_repost = 14
        const val profile_message = 15
    }
}