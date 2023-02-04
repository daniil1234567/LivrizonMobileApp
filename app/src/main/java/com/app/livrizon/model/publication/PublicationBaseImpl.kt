package com.app.livrizon.model.publication

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.type.PublicationType

abstract class PublicationBaseImpl(
    val publication_id: Int,
    val type: PublicationType,
    val date:Long
) : java.io.Serializable {
    abstract val from: ProfileBase
}